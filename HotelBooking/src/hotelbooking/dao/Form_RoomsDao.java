/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import hotelbooking.model.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Form_RoomsDao {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "sys as sysdba";
    private static final String DB_PASSWORD = "sys";
    
    public Connection connectToOracle() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    public List<Room> getAllRooms() throws SQLException, ClassNotFoundException{
        List<Room> list = new ArrayList<>();
        try{
            // Connect to the database
            Connection con = connectToOracle();
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
            // Close resources
            rs.close();
            pst.close();
            con.close();   
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }
}
