/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Form_RegisterDao {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "sys as sysdba";
    private static final String DB_PASSWORD = "sys";
    
    public Connection connectToOracle() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    public void insertUserInfo(String username, String fullname, String gmail, String address, String phone)throws SQLException{
        try {
            Connection connection = connectToOracle();
            String insertSql = "{call insert_user_info(?, ?, ?, ?, ?)}";
            
            // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
            CallableStatement insertStatement = connection.prepareCall(insertSql);
            insertStatement.setString(1, username);
            insertStatement.setString(2, fullname);
            insertStatement.setString(3, gmail);
            insertStatement.setString(4, address);
            insertStatement.setString(5, phone);
            insertStatement.execute();
            insertStatement.close();
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createUserWithPermissions(String username, String password)throws SQLException{
        try {
            Connection connection = connectToOracle();
            String createSql = "{call create_user_with_permissions(?, ?)}";
            
            // Chuẩn bị và thi hành lệnh gọi stored procedure create_user_with_permissions
            CallableStatement createStatement = connection.prepareCall(createSql);
            createStatement.setString(1, username);
            createStatement.setString(2, password);
            createStatement.execute();
            createStatement.close();

            // Đóng kết nối và giải phóng tài nguyên
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Form_RegisterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
