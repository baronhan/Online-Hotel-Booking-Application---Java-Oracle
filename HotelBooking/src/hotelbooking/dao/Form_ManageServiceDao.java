/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import hotelbooking.model.Service;
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

/**
 *
 * @author DELL
 */
public class Form_ManageServiceDao {
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
    public List<Service> getAllService(String username, String password)throws SQLException{
        List<Service> list = new ArrayList<>();
        try {
            Connection con = connectToOracle(username, password);
            String sql = "SELECT * FROM hotelbooking.services";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            //Process the result set and populate the list
            while(rs.next()){
                String serviceId = rs.getString("serviceId");
                String serviceName = rs.getString("serviceName");
                String acronym = rs.getString("acronym");
                float price = rs.getFloat("price");
                String serviceType = rs.getString("serviceType");
                Service service = new Service(serviceId, serviceName, acronym, price, serviceType);
                list.add(service);
            }
            // Close resources
            rs.close();
            pst.close();
            con.close();   
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void insertService(String username, String password, String servicename, String acronym, float price, String servicetype){
        try {
            Connection connection = connectToOracle(username, password);
            // Gọi stored procedure insert_room từ Java
            CallableStatement cst = connection.prepareCall("{call insert_service(?,?,?,?)}");
            cst.setString(1, servicename);
            cst.setString(2, acronym);
            cst.setDouble(3, price);
            cst.setString(4, servicetype);
            cst.execute();
            cst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateServiceByServiceName(String username, String password, String servicename, String acronym, float price, String servicetype){
        try {
            Connection connection = connectToOracle(username, password);
            // Gọi stored procedure
            CallableStatement cst = connection.prepareCall("{call update_service_by_service_name(?,?,?,?)}");
            cst.setString(1, servicename);
            cst.setString(2, acronym);
            cst.setFloat(3, price);
            cst.setString(4, servicetype);
            
            cst.execute();
            cst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteServiceByServiceName(String username, String password, String serviceName)throws SQLException{
        try {
            Connection connection = connectToOracle(username, password);
            //Chuẩn bị câu lệnh sp
            String deleteuser = "{call delete_service_by_service_name(?)}";
            //Chuẩn bị và thi hành câu lệnh sp
            CallableStatement deleteStatement = connection.prepareCall(deleteuser);
            deleteStatement.setString(1, serviceName);
            deleteStatement.execute();
            deleteStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
