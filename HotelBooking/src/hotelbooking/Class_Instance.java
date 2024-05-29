package hotelbooking;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Class_Instance extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    public Class_Instance()
    {
        setTitle("Instance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        model.addColumn("INSTANCE_NAME");
        model.addColumn("INSTANCE_NUMBER");
        model.addColumn("HOST_NAME");
        model.addColumn("VERSION");
        model.addColumn("STARTUP_TIME");
        
        loadDataFromDatabase();

        setVisible(true);
    }
    private void loadDataFromDatabase() {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // Thay đổi thông tin kết nối của bạn
        String username = "sys as sysdba";
        String password = "sys";
        String query = "SELECT INSTANCE_NAME, INSTANCE_NUMBER, HOST_NAME, VERSION, STARTUP_TIME FROM v$instance";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String instanceName = resultSet.getString("INSTANCE_NAME");
            String instanceNumber = resultSet.getString("INSTANCE_NUMBER");
            String hostName = resultSet.getString("HOST_NAME");
            String version = resultSet.getString("VERSION");
            String startupTime = resultSet.getString("STARTUP_TIME");
            String[] tbData = {instanceName, instanceNumber, hostName, version, startupTime};
            model.addRow(tbData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Class_Instance();
    }
}
