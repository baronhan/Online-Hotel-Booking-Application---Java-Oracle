/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import static hotelbooking.Form_Booking.UserId;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Form_BookingDao {
    public Connection connectToOracle(String username, String password) throws SQLException {
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
        //return Dao_Booking.getConnection(username, password);
    }
    
    public boolean CheckDates(String username, String password, java.sql.Date sqlDateCheckIn, java.sql.Date sqlDateCheckOut){
        boolean result = true;
        try {
            Connection conn = connectToOracle(username, password);
            CallableStatement stmt_checkdate = conn.prepareCall("{ ? = call SYS.CheckDates(?, ?) }");
            
            // Truyền vào các tham số kiểu DATE
            //                stmt_checkdate.setString(2, strDateCheckIn);
            //                stmt_checkdate.setString(3, strDateCheckOut);
            stmt_checkdate.setDate(2, sqlDateCheckIn);
            stmt_checkdate.setDate(3, sqlDateCheckOut);

            // Đăng ký tham số đầu ra và thực thi câu lệnh
            stmt_checkdate.registerOutParameter(1, Types.BOOLEAN);
            stmt_checkdate.execute();

            // Lấy kết quả từ tham số đầu ra
            result = stmt_checkdate.getBoolean(1);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_BookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public void AddBooking(String username, String password, String UserId, String roomId, String serviceId, java.sql.Date sqlDateCheckIn, java.sql.Date sqlDateCheckOut, String CardCreditNumber, float Total)throws SQLException {
        try {
            Connection conn = connectToOracle(username, password);
            CallableStatement stmt_addbooking = conn.prepareCall("{call SYS.AddBooking(?, ?, ?, ?, ? , ?, ?)}");
            stmt_addbooking.setString(1, UserId);
            stmt_addbooking.setString(2, roomId);
            stmt_addbooking.setString(3, serviceId);
            stmt_addbooking.setDate(4, sqlDateCheckIn);
            stmt_addbooking.setDate(5, sqlDateCheckOut);
            stmt_addbooking.setString(6, CardCreditNumber);
            stmt_addbooking.setFloat(7, Total);
            
            //Thực thi
            stmt_addbooking.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_BookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void GetRoomInfo(String username, String password, String roomId, String RoomName, float RoomPrice, String RoomPicture){
        try {
            Connection conn = connectToOracle(username, password);
            CallableStatement stmt_getroominfo = conn.prepareCall("{call SYS.GetRoomInfo(?, ?, ?, ?)}");
            stmt_getroominfo.setString(1, roomId); // Tham số đầu vào
            stmt_getroominfo.registerOutParameter(2, Types.VARCHAR); // Tham số đầu ra: roomName
            stmt_getroominfo.registerOutParameter(3, Types.FLOAT); // Tham số đầu ra: price
            stmt_getroominfo.registerOutParameter(4, Types.VARCHAR); // Tham số đầu ra: roomPicture

            // Thực thi stored procedure
            stmt_getroominfo.execute();

            // Lấy kết quả
            RoomName = stmt_getroominfo.getString(2);
            RoomPrice = stmt_getroominfo.getFloat(3);
            RoomPicture = stmt_getroominfo.getString(4);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_BookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void GetUserInfo(String username, String password, String OrderBy, String EmailAddress, String Address, String PhoneNumber, String UserId){
        try {
            Connection conn = connectToOracle(username, password);
            CallableStatement stmt_getuserinfo = conn.prepareCall("{call SYS.GetUserInfo(?, ?, ?, ?, ?, ?)}");
            stmt_getuserinfo.setString(1, username);
            stmt_getuserinfo.registerOutParameter(2, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(3, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(4, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(5, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(6, Types.VARCHAR);
            
            // Thực thi stored procedure
            stmt_getuserinfo.execute();
            
            //Lấy kết quả
            OrderBy = stmt_getuserinfo.getString(2);
            EmailAddress = stmt_getuserinfo.getString(3);
            Address = stmt_getuserinfo.getString(4);
            PhoneNumber = stmt_getuserinfo.getString(5);
            UserId = stmt_getuserinfo.getString(6);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_BookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void GetServiceInfo(String username, String password, String ServiceName, String serviceId, float ServicePrice)throws SQLException{
        try {
            Connection conn = connectToOracle(username, password);
            CallableStatement stmt_getserviceinfo = conn.prepareCall("{call SYS.GetServiceInfo(?, ?, ?)}");
            stmt_getserviceinfo.setString(1, serviceId);
            stmt_getserviceinfo.registerOutParameter(2, Types.VARCHAR);
            stmt_getserviceinfo.registerOutParameter(3, Types.FLOAT);
            
            //Thực thi stored procedure
            stmt_getserviceinfo.execute();
            
            //Lấy kết quả
            ServiceName = stmt_getserviceinfo.getString(2);
            ServicePrice = stmt_getserviceinfo.getFloat(3);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_BookingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
