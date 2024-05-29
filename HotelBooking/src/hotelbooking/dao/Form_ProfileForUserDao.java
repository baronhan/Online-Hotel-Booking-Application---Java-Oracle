/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABB
 */
public class Form_ProfileForUserDao {
    private String usernameClickString;
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
    
    public void SYS_CREATE_PROFILE(String username, String password ,String profileName ,int session,int connectionTime,int reUsePass,int failLogin,int passLock)throws SQLException
    {
        try {
            Connection connection = connectToOracle(username, password);
             // Chuẩn bị câu lệnh SQL để gọi stored procedure
            String createProfileSql = "{call SYS.CREATE_PROFILE(?, ?, ?, ?, ?, ?)}";
            CallableStatement createProfile = connection.prepareCall(createProfileSql);
            createProfile.setString(1, profileName);
            createProfile.setInt(2, session);
            createProfile.setInt(3, connectionTime);
            createProfile.setInt(4, reUsePass);
            createProfile.setInt(5, failLogin);
            createProfile.setInt(6, passLock);
            createProfile.execute();
            createProfile.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ProfileForUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SYS_DELETE_PROFILE(String username, String password, String profileName)throws SQLException
    {
        try {
            Connection connection = connectToOracle(username, password);
            // Chuẩn bị câu lệnh SQL để gọi stored procedure
            String deleteProfileSql = "{call SYS.DELETE_PROFILE(?)}";
            
            // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
            CallableStatement deleteProfile = connection.prepareCall(deleteProfileSql);
            deleteProfile.setString(1, profileName);
            deleteProfile.execute();
            deleteProfile.close();
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ProfileForUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SYS_UPDATE_PROFILE_USER(String username, String password,String profileString)throws SQLException
    {
        try {
            Connection connection = connectToOracle(username, password);
            // Chuẩn bị câu lệnh SQL để gọi stored procedure
            String updateProfileSql = "{call SYS.UPDATE_PROFILE_USER(?, ?)}";
            
            // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
            CallableStatement updateProfile = connection.prepareCall(updateProfileSql);
            updateProfile.setString(1, usernameClickString);
            updateProfile.setString(2, profileString);
            updateProfile.execute();
            updateProfile.close();
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ProfileForUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PreparedStatement SYS_MY_PROFILES_VIEW(String username, String password)
    {
        PreparedStatement pstMyProfilesView=null;
        try {
           
            Connection connection = connectToOracle(username, password);
            String sqlMyProfilesView = "SELECT PROFILE FROM SYS.MY_PROFILES_VIEW";
            pstMyProfilesView = connection.prepareStatement(sqlMyProfilesView);
            pstMyProfilesView.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ProfileForUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pstMyProfilesView;
    }
    
    public PreparedStatement hotelbooking_user_info(String username, String password)
    {
        PreparedStatement pstUserInfo=null;
        try {
            Connection connection = connectToOracle(username, password);
            String sqlUserInfo = "SELECT username FROM hotelbooking.user_info";
            pstUserInfo = connection.prepareStatement(sqlUserInfo);
            pstUserInfo.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ProfileForUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pstUserInfo;
    }
}
