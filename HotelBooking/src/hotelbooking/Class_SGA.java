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

public class Class_SGA extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    public Class_SGA(){
        setTitle("SGA");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        model.addColumn("NAME");
        model.addColumn("VALUE");
        model.addColumn("CON_ID");

        loadDataFromDatabase();

        setVisible(true);
    }
    private void loadDataFromDatabase() {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // Thay đổi thông tin kết nối của bạn
        String username = "sys as sysdba";
        String password = "sys";
        String query = "SELECT * FROM v$sga";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String value = resultSet.getString("VALUE");
                String conID = resultSet.getString("CON_ID");
                String[] tbData = {name,value, conID};
                model.addRow(tbData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Class_SGA();
    }
}
