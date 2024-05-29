package hotelbooking;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Class_SessionInfo extends JFrame {
    private DefaultTableModel model;
    private JTable table;

    public Class_SessionInfo() {
        // Thêm nút "Xóa" vào giao diện
        JButton deleteButton = new JButton("Xóa");
        getContentPane().add(deleteButton);
        
        setTitle("Session Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        
        model.addColumn("NAME");
        model.addColumn("SID");
        model.addColumn("Serial#");
        model.addColumn("Program");

        loadDataFromDatabase();
        
        // Thêm sự kiện lắng nghe cho sự kiện click vào dòng trong bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String name = (String) table.getValueAt(row, 0);
                    // Gọi hàm xóa session dựa trên SID
                    deleteSession(name);
                }
            }
        });
        setVisible(true);
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        clearTable();
        String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // Thay đổi thông tin kết nối của bạn
        String username = "sys as sysdba";
        String password = "sys";
        String query = "SELECT username ,sid, serial#, program FROM v$session WHERE type != 'BACKGROUND'";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("username");
                int sid = resultSet.getInt("sid");
                int serial = resultSet.getInt("serial#");
                String program = resultSet.getString("program");
                model.addRow(new Object[]{name, sid, serial, program});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteSession(String name) {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "sys as sysdba";
        String password = "sys";
        String killSql = "{call kill_user_sessions(?)}";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             CallableStatement disconnectProc = connection.prepareCall(killSql)) {

            disconnectProc.setString(1, name.toUpperCase());
            disconnectProc.execute();

            // Sau khi xóa thành công, cập nhật lại bảng
            loadDataFromDatabase();

            JOptionPane.showMessageDialog(this, "Xóa session thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to close connection. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void clearTable() {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static void main(String[] args) {
        new Class_SessionInfo();
        
    }
}
