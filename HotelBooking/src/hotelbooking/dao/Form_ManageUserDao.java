/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import hotelbooking.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABB
 */
public class Form_ManageUserDao {
    private Connection connectToOracle(String username, String password) {
        Connection connection = null;
        try {
            // Xây dựng URL kết nối dựa trên database
            String url = "jdbc:oracle:thin:@:1521:orcl"; // Thay localhost và port nếu cần

            // Kết nối đến cơ sở dữ liệu
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url,"sys as sysdba", "sys");
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed. Error: " + e.getMessage());
        }
        return connection;
    }
    
    public void update_user_info_by_username(String username, String password, String userName,String fullName,String address,String Phone,String Gmail)throws SQLException
    {
        try {
            Connection connection = connectToOracle(username,password);
            //Chuẩn bị câu lệnh sp
            String updateuser = "{call update_user_info_by_username(?, ?, ?, ?, ?)}";
            
            //Chuẩn bị và thi hành câu lệnh sp
            CallableStatement updateStatement = connection.prepareCall(updateuser);
            updateStatement.setString(1, userName);
            updateStatement.setString(2, fullName);
            updateStatement.setString(3, address);
            updateStatement.setString(4, Phone);
            updateStatement.setString(5, Gmail);
            updateStatement.execute();
            updateStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete_user_by_username(String username, String password, String UserName)throws SQLException
    {
        try {
            Connection connection = connectToOracle(username,password);
            //Chuẩn bị câu lệnh sp
            String deleteuser = "{call delete_user_by_username(?)}";
            //Chuẩn bị và thi hành câu lệnh sp
            CallableStatement deleteStatement = connection.prepareCall(deleteuser);
            deleteStatement.setString(1, UserName);
            deleteStatement.execute();
            deleteStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CallableStatement get_suggested_username(String username, String password, String userName)
    {
        CallableStatement suggestionStatement=null;
        try {
            Connection connection = connectToOracle(username,password);
            // Gọi function để lấy username được gợi ý từ cơ sở dữ liệu
            suggestionStatement = connection.prepareCall("{ ? = call get_suggested_username(?) }");
            suggestionStatement.registerOutParameter(1, Types.VARCHAR);
            suggestionStatement.setString(2, userName);
            suggestionStatement.execute();
            suggestionStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suggestionStatement;
    }
    
    public CallableStatement check_username_exist(String username, String password, String userName)
    {
        CallableStatement checkStatement=null;
        try {
            Connection connection = connectToOracle(username,password);
            // Kiểm tra username đã tồn tại hay chưa
            checkStatement = connection.prepareCall("{ call check_username_exist(?, ?) }");
            checkStatement.setString(1, userName);
            checkStatement.registerOutParameter(2, Types.INTEGER);
            checkStatement.execute();
            checkStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkStatement;
    }
    
    public void insert_user_info(String username, String password,String username_, String fullname, String email, String address, String phone)
    {
        try {
            Connection connection = connectToOracle(username,password);
             // Chuẩn bị câu lệnh SQL để gọi stored procedure insert_user_info
            String insertSql = "{call insert_user_info(?, ?, ?, ?, ?)}";
            CallableStatement insertStatement = connection.prepareCall(insertSql);
            insertStatement.setString(1, username_);
            insertStatement.setString(2, fullname);
            insertStatement.setString(3, email);
            insertStatement.setString(4, address);
            insertStatement.setString(5, phone);
            insertStatement.execute();
            insertStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create_user_with_permissions(String username, String password,String username_, String password_, String profileString)
    {
        try {
            Connection connection = connectToOracle(username,password);
            // Chuẩn bị câu lệnh SQL để gọi stored procedure create_user_with_permissions
            String createSql = "{call create_user_with_permissions(?, ?, ?)}";
            
            // Chuẩn bị và thi hành lệnh gọi stored procedure create_user_with_permissions
            CallableStatement createStatement = connection.prepareCall(createSql);
            createStatement.setString(1, username_);
            createStatement.setString(2, password_);
            createStatement.setString(3, profileString);
            createStatement.execute();
            createStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void UPDATE_PROFILE_USER(String username, String password,String username_, String profileString)throws SQLException
    {
        try {         
            Connection connection = connectToOracle(username,password);
            // Chuẩn bị câu lệnh SQL để gọi stored procedure
            String updateProfileSql = "{call UPDATE_PROFILE_USER(?, ?)}";

            // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
            CallableStatement updateProfile = connection.prepareCall(updateProfileSql);
            updateProfile.setString(1, username_);
            updateProfile.setString(2, profileString);
            updateProfile.execute();
            updateProfile.close();

            connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    
    public void GRANT_PROFILE_PERMISSIONS(String username, String password,String username_)throws SQLException
    {
        try {
            Connection connection = connectToOracle(username,password);
            // Chuẩn bị câu lệnh SQL để gọi stored procedure
            String grantPermissionsSql = "{call GRANT_PROFILE_PERMISSIONS(?)}";
            
            // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
            CallableStatement grantPermissions = connection.prepareCall(grantPermissionsSql);
            grantPermissions.setString(1, username_);
            grantPermissions.execute();
            grantPermissions.close();
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void REVOKE_PROFILE_PERMISSIONS(String username, String password,String username_)throws SQLException
    {
        try {
            Connection connection = connectToOracle(username,password);
            // Chuẩn bị câu lệnh SQL để gọi stored procedure
            String revokePermissionsSql = "{call REVOKE_PROFILE_PERMISSIONS(?)}";
            
            // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
            CallableStatement revokePermissions = connection.prepareCall(revokePermissionsSql);
            revokePermissions.setString(1, username_);
            revokePermissions.execute();
            revokePermissions.close();
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<User> hotelbooking_user_info(String username, String password) throws SQLException
    {
        List<User> list = new ArrayList<>();
        try {
            
            
            // Connect to the database
            Connection connection = connectToOracle(username,password);
            String sql = "SELECT * FROM hotelbooking.user_info";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            //Process the result set and populate the list
            while(rs.next()){
                String userId = rs.getString("UserId");
                String userName = rs.getString("UserName");
                String fullName = rs.getString("FullName");
                String userEmail = rs.getString("UserEmail");
                String userAddress = rs.getString("UserAddress");
                String userPhone = rs.getString("UserPhone");
                User user = new User(userId, userName, fullName, userEmail, userAddress, userPhone);
                list.add(user);
            }
            // Close resources
            rs.close();
            pst.close();
            connection.close();   
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public PreparedStatement DISTINCT_PROFILE_FROM_DBA_PROFILES(String username, String password) 
    {
        PreparedStatement pst=null;
        try {
            
            Connection connection = connectToOracle(username,password);
            String sql = "SELECT DISTINCT PROFILE FROM DBA_PROFILES";
            pst = connection.prepareStatement(sql);
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_ManageUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pst;
    }
}
