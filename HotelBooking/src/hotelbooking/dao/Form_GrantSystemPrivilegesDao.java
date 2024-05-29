/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author ABB
 */
public class Form_GrantSystemPrivilegesDao {
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
    public int checkPrivilegeExistence(String username, String password,String usernameIsClicked,String privilegeIsClicked)
    {
        int result = 0;
        try {
            Connection connection = connectToOracle(username,password);
                    String checkStatement = "{call checkPrivilegeExistence(?, ?, ?)}";
                    // Tạo đối tượng CallableStatement để gọi stored procedure
                    CallableStatement checkStatement_ = connection.prepareCall(checkStatement);

                    // Đặt các tham số cho stored procedure
                    checkStatement_.setString(1, usernameIsClicked);
                    checkStatement_.setString(2, privilegeIsClicked);

                    // Đăng ký tham số đầu ra
                    checkStatement_.registerOutParameter(3, Types.NUMERIC);
                    // Thực thi stored procedure
                    checkStatement_.execute();
                    result = checkStatement_.getInt(3);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;          
    }
    
    public void grantPrivilegeWithAdminOption(String username, String password,String usernameIsClicked,String privilegeIsClicked)
    {
        try {
            Connection connection = connectToOracle(username, password);
            String callStatement = "{call grantPrivilege(?, ?)}";
            // Tạo đối tượng CallableStatement để gọi stored procedure
            CallableStatement callableStatement = connection.prepareCall(callStatement);
            
            // Đặt các tham số cho stored procedure
            callableStatement.setString(1, usernameIsClicked);
            callableStatement.setString(2, privilegeIsClicked);
            
            // Thực thi stored procedure
            callableStatement.execute();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void grantPrivilege(String username, String password,String usernameIsClicked,String privilegeIsClicked)
    {
        try {
            Connection connection = connectToOracle(username, password);
            String callStatement = "{call grantPrivilege(?, ?)}";
            // Tạo đối tượng CallableStatement để gọi stored procedure
            CallableStatement callableStatement = connection.prepareCall(callStatement);
            
            // Đặt các tham số cho stored procedure
            callableStatement.setString(1, usernameIsClicked);
            callableStatement.setString(2, privilegeIsClicked);
            
            // Thực thi stored procedure
            callableStatement.execute();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int checkPrivilegeExistence1(String username, String password,String usernameIsClicked,String privilegeIsClicked)
    {
        int result = 0;
        try {
            Connection connection = connectToOracle(username, password);
            String checkStatement = "{call checkPrivilegeExistence(?, ?, ?)}";
            // Tạo đối tượng CallableStatement để gọi stored procedure
            CallableStatement checkStatement_ = connection.prepareCall(checkStatement);
            
            // Đặt các tham số cho stored procedure
            checkStatement_.setString(1, usernameIsClicked);
            checkStatement_.setString(2, privilegeIsClicked);
            
            // Đăng ký tham số đầu ra
            checkStatement_.registerOutParameter(3, Types.NUMERIC);
            // Thực thi stored procedure
            checkStatement_.execute();
            result = checkStatement_.getInt(3);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void REVOKE_WITH_ADMIN_OPTION(String username, String password,String usernameIsClicked,String privilegeIsClicked)
    {
        try {
            Connection connection = connectToOracle(username, password);
            // Tạo đối tượng CallableStatement để gọi stored procedure
            CallableStatement callableStatement = connection.prepareCall("{call REVOKE_WITH_ADMIN_OPTION(?, ?)}");
            
            // Đặt các tham số cho stored procedure
            callableStatement.setString(1, usernameIsClicked.toUpperCase());
            callableStatement.setString(2, privilegeIsClicked);
            
            // Thực thi stored procedure
            callableStatement.execute();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void revokeSpecificPrivilege(String username, String password,String usernameIsClicked,String privilegeIsClicked)
    {
        try {
            Connection connection = connectToOracle(username, password);
            String callStatement = "{call revokeSpecificPrivilege(?, ?)}";
            // Tạo đối tượng CallableStatement để gọi stored procedure
            CallableStatement callableStatement = connection.prepareCall(callStatement);
            
            // Đặt các tham số cho stored procedure
            callableStatement.setString(1, usernameIsClicked);
            callableStatement.setString(2, privilegeIsClicked);
            
            // Thực thi stored procedure
            callableStatement.execute();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public PreparedStatement open_users_vieẉ̣̣̣̣̣̣̣̣̣̣(String username, String password)
    {
        PreparedStatement statement1=null;
        try {
            Connection connection = connectToOracle(username, password);
            String query1 = "SELECT * FROM open_users_view";
            statement1 = connection.prepareStatement(query1);
            statement1.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement1;
    }
    
    public PreparedStatement sys_privs_view(String username, String password)
    {
        PreparedStatement statement2=null;
        try {
            Connection connection = connectToOracle(username, password);
            String query2 = "SELECT * FROM sys_privs_view";
            statement2 = connection.prepareStatement(query2);
            statement2.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Form_GrantSystemPrivilegesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement2;
    }
}
