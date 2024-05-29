package hotelbooking.dao;

import java.sql.*;

public class Form_HomeDao {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "sys as sysdba";
    private static final String DB_PASSWORD = "sys";

    public Connection connectToOracle() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public String getLastLogin(String username) throws SQLException, ClassNotFoundException {
        String lastLoginValue = null;
        String lastLoginSQL = "{call get_last_login(?, ?)}";

        try (Connection connection = connectToOracle();
             CallableStatement lastLogin = connection.prepareCall(lastLoginSQL)) {
            
            lastLogin.setString(1, username.toUpperCase());
            lastLogin.registerOutParameter(2, Types.VARCHAR);
            lastLogin.execute();
            lastLoginValue = lastLogin.getString(2);
        }

        return lastLoginValue;
    }

    public boolean checkSession(String username) throws SQLException, ClassNotFoundException {
        boolean sessionExists = true;
        String checkSessionSQL = "{call check_session(?, ?)}";

        try (Connection connection = connectToOracle();
             CallableStatement cal = connection.prepareCall(checkSessionSQL)) {
            
            cal.setString(1, username.toUpperCase());
            cal.registerOutParameter(2, Types.BOOLEAN);
            cal.execute();
            sessionExists = cal.getBoolean(2);
        }

        return sessionExists;
    }

    public void killUserSessions(String username) throws SQLException, ClassNotFoundException {
        String disconnectSQL = "{call kill_user_sessions(?)}";

        try (Connection connection = connectToOracle();
             CallableStatement disconnectProc = connection.prepareCall(disconnectSQL)) {
            
            disconnectProc.setString(1, username.toUpperCase());
            disconnectProc.execute();
        }
    }

    public int checkInsertPrivilege(String username) throws SQLException, ClassNotFoundException {
        int result;
        String checkInsertPrivilegeSQL = "{call Check_Insert_Privilege(?, ?)}";

        try (Connection connection = connectToOracle();
             CallableStatement cstmt = connection.prepareCall(checkInsertPrivilegeSQL)) {
            
            cstmt.setString(1, username.toUpperCase());
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.execute();
            result = cstmt.getInt(2);
        }

        return result;
    }

    public int checkSelectPrivilege(String username) throws SQLException, ClassNotFoundException {
        int result;
        String checkSelectPrivilegeSQL = "{call Check_Select_Privilege(?, ?)}";

        try (Connection connection = connectToOracle();
             CallableStatement selectStmt = connection.prepareCall(checkSelectPrivilegeSQL)) {
            
            selectStmt.setString(1, username.toUpperCase());
            selectStmt.registerOutParameter(2, Types.INTEGER);
            selectStmt.execute();
            result = selectStmt.getInt(2);
        }

        return result;
    }

    public int checkUpdatePrivilege(String username) throws SQLException, ClassNotFoundException {
        int result;
        String checkUpdatePrivilegeSQL = "{call Check_Update_Privilege(?, ?)}";

        try (Connection connection = connectToOracle();
             CallableStatement updateStmt = connection.prepareCall(checkUpdatePrivilegeSQL)) {
            
            updateStmt.setString(1, username.toUpperCase());
            updateStmt.registerOutParameter(2, Types.INTEGER);
            updateStmt.execute();
            result = updateStmt.getInt(2);
        }

        return result;
    }
}
