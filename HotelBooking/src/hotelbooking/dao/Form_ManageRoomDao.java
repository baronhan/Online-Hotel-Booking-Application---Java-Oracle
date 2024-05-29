/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import hotelbooking.model.Room;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Form_ManageRoomDao {
    public Connection connectToOracle(String username, String password) {
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
    public List<Room> getAllRooms(String username, String password)throws SQLException{
        List<Room> list = new ArrayList<>();
        try {
            Connection con = connectToOracle(username, password);
            String sql = "SELECT * FROM hotelbooking.rooms";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            //Process the result set and populate the list
            while(rs.next()){
                String roomId = rs.getString("roomId");
                String roomName = rs.getString("roomName");
                int noPeople = rs.getInt("noPeople");
                float price = rs.getFloat("price");
                String roomDescription = rs.getString("roomDescription");
                String roomType = rs.getString("roomType");
                String roomPicture  = rs.getString("roomPicture");
                boolean roomStatus = rs.getBoolean("roomStatus");
                Room room = new Room(roomId, roomName, noPeople, price, roomDescription, roomType, roomPicture, roomStatus);
                list.add(room); 
            }   
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageRoomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void insertRoom(String username, String password, String roomname, int noofpeople, float price, String roomdescription, String roomtype, String roompicture,int roomstatus){
        try {
            Connection con = connectToOracle(username, password);
            // Gọi stored procedure insert_room từ Java
            CallableStatement cst = con.prepareCall("{call insert_room(?,?,?,?,?,?,?)}");
            cst.setString(1, roomname);
            cst.setInt(2, noofpeople);
            cst.setDouble(3, price);
            cst.setString(4, roomdescription);
            cst.setString(5, roomtype);
            cst.setString(6, roompicture);
            cst.setInt(7, roomstatus);
            
            cst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageRoomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateRoomInfoByRoomName(String username, String password, String roomname, int noofpeople, float price, String roomdescription, String roomtype, String roompicture,int roomstatus){
        try {
            Connection con = connectToOracle(username, password);
            // Gọi stored procedure
            CallableStatement cst = con.prepareCall("{call update_room_info_by_room_name(?,?,?,?,?,?,?)}");
            cst.setString(1, roomname);
            cst.setInt(2, noofpeople);
            cst.setFloat(3, price);
            cst.setString(4, roomdescription);
            cst.setString(5, roomtype);
            cst.setString(6, roompicture);
            cst.setInt(7, roomstatus);
            
            cst.execute();
            cst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageRoomDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteRoomByRoomName(String username, String password, String roomName)throws SQLException{
        try {
            Connection con = connectToOracle(username, password);
            //Chuẩn bị câu lệnh sp
            String deleteuser = "{call delete_room_by_room_name(?)}";
            //Chuẩn bị và thi hành câu lệnh sp
            CallableStatement deleteStatement = con.prepareCall(deleteuser);
            deleteStatement.setString(1, roomName);
            deleteStatement.execute();
            deleteStatement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thực thi stored procedure. Error: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
