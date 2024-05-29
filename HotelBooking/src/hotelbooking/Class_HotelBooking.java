
package hotelbooking;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import javax.swing.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Class_HotelBooking {

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Hiển thị form đăng nhập
            Form_Login form = new Form_Login();
            form.setVisible(true); // Hiển thị form đăng nhậ
            form.addFormReadyListener(evt -> {
                // Form đã sẵn sàng, thực thi Swing Worker để kiểm tra session
                CheckSessionWorker worker = new CheckSessionWorker(form);
                worker.execute();
            });
            
        });
    }

    private static class CheckSessionWorker extends SwingWorker<Boolean, Void> {
        private final Form_Login form_Login;

        public CheckSessionWorker(Form_Login form) {
            this.form_Login = form;
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            String url = "jdbc:oracle:thin:@:1521:orcl";
            String username = "sys as sysdba";
            String password = "sys";
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                try (CallableStatement cal = connection.prepareCall("{call check_session(?, ?)}")) {
                    // Lấy giá trị username từ form_Login
                    String currentUsername = form_Login.userName;
                    System.out.println(currentUsername);
                    cal.setString(1, currentUsername.toUpperCase());
                    cal.registerOutParameter(2, Types.BOOLEAN);
                    cal.execute();

                    // Lấy giá trị sessionExists sau khi thực hiện stored procedure
                    boolean sessionExists = cal.getBoolean(2);

                    // Đóng kết nối
                    cal.close();

                    return sessionExists;
                }
            } catch (Exception e) {
                // Xử lý lỗi, ví dụ: thông báo hoặc ghi vào nhật ký
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void done() {
            try {
                boolean sessionValid = get(); // Lấy kết quả từ doInBackground()
                System.out.println(sessionValid);
                if (!sessionValid) {
                    // Nếu session không tồn tại, đóng form đăng nhập
                    Form_Home homeInstance = form_Login.getFormHome();
                    if (homeInstance != null) {
                        homeInstance.setVisible(false);
                        homeInstance.dispose();
                        JOptionPane.showMessageDialog(null, "Session expired. You have been logged out.");
                    }
                } else {
                    System.out.println("bbb");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(Class_HotelBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}

    
