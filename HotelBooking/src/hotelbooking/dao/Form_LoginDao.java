package hotelbooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Form_LoginDao {
    public Connection connectToOracle(String database, String username, String password) {
        Connection connection = null;
        try {
            String url = "jdbc:oracle:thin:@:1521:" + database;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            if (e.getErrorCode() == 28000) {
                JOptionPane.showMessageDialog(null, "Tài khoản bạn đã bị khóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Connection failed. Error: " + e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found. Error: " + e.getMessage());
        }
        return connection;
    }
}
