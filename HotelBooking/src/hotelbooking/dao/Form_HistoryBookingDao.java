/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import hotelbooking.model.BookingHistory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class Form_HistoryBookingDao {
    public Connection connectToOracle(String username, String password){
        Connection connection = null;
        try {
            // Xây dựng URL kết nối dựa trên database
            String url = "jdbc:oracle:thin:@:1521:orcl"; // Thay localhost và port nếu cần

            // Kết nối đến cơ sở dữ liệu
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url,username, password);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed. Error: " + e.getMessage());
        }
        return connection;
    }
    
    public String GetUserIdByUsername(String username, String password){
        String userid = null;
        try { 
            Connection con = connectToOracle(username, password);
            // Prepare the call to stored procedure to get userId from username
            CallableStatement userinfor = con.prepareCall("{CALL SYS.GetUserIdByUsername(?, ?)}");
            userinfor.setString(1, username);
            userinfor.registerOutParameter(2, Types.VARCHAR);
            userinfor.execute();

            // Retrieve userId
            userid = userinfor.getString(2);
            userinfor.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Form_HistoryBookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userid;
    }
    
    public List<BookingHistory> GetAllBookingDetails(String username, String password)throws SQLException{
        List<BookingHistory> list = new ArrayList<>();
        try {
            Connection con = connectToOracle(username, password);
            CallableStatement cstmt = con.prepareCall("{CALL SYS.GetAllBookingDetails(?)}");
            
            // Register the OUT parameter for the result set
            cstmt.registerOutParameter(1, OracleTypes.CURSOR); // bookings

            // Execute the stored procedure
            cstmt.execute();

            // Retrieve the result set
            ResultSet rs = (ResultSet) cstmt.getObject(1);

            // Process each row in the result set
            while (rs.next()) {
                // Retrieve data from the result set
                String bookingId = rs.getString("bookingId");
                String userId = rs.getString("userId");
                String roomName = rs.getString("roomName");
                int noOfPeople = rs.getInt("noPeople");
                String roomType = rs.getString("roomType");
                String serviceName = rs.getString("serviceName");
                float servicePrice = rs.getFloat("price");
                float roomPrice = rs.getFloat("price");
                float total = rs.getFloat("total");
                Date checkIn = rs.getDate("checkIn");
                Date checkOut = rs.getDate("checkOut");

                // Create BookingHistory object
                BookingHistory booking = new BookingHistory(bookingId, userId, roomName, noOfPeople, roomType, serviceName, servicePrice, roomPrice, total, checkIn, checkOut);
                list.add(booking);
            }

            // Close resources
            rs.close();
            cstmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_HistoryBookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
