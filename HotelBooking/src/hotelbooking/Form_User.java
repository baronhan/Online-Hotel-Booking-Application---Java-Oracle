package hotelbooking;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import hotelbooking.Form_Home;
import static hotelbooking.Form_Home.username1;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Types;
import javax.imageio.ImageIO;


public class Form_User extends javax.swing.JFrame {

    String url = "jdbc:oracle:thin:@localhost:1521:orcl"; 
    String username = Form_Login.userName; 
    String pass = Form_Login.password;
    public Form_User() {
        initComponents();
        fillTable();
        fillProfileInfor();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGmail = new javax.swing.JTextField();
        btnCapNhat = new javax.swing.JButton();
        btnVeTrangChu = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtProfileName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPasswordFailureLimit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIncorrectPassword = new javax.swing.JTextField();
        btnProfileManage = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BtnCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("UserName:");

        txtUserName.setEditable(false);

        jLabel3.setText("FullName:");

        jLabel4.setText("Address:");

        jLabel5.setText("Phone:");

        jLabel6.setText("Gmail:");

        btnCapNhat.setText("Update");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnVeTrangChu.setText("Home");
        btnVeTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVeTrangChuActionPerformed(evt);
            }
        });

        jLabel7.setText("Profile Name:");

        txtProfileName.setEditable(false);
        txtProfileName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel8.setText("Password Failure Limit: ");

        txtPasswordFailureLimit.setEditable(false);

        jLabel9.setText("Incorrect password deletion :");

        txtIncorrectPassword.setEditable(false);

        btnProfileManage.setText("Mange Profile");
        btnProfileManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileManageActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USER INFORMATION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        BtnCreate.setText("Create QR");
        BtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFullName)
                            .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnProfileManage, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(BtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIncorrectPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                    .addComponent(txtPasswordFailureLimit, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtProfileName, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(8, 8, 8)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswordFailureLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIncorrectPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat)
                    .addComponent(btnVeTrangChu)
                    .addComponent(btnProfileManage)
                    .addComponent(BtnCreate))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private Connection connectToOracle() {
        Connection connection = null;
        try {
          // Xây dựng URL kết nối dựa trên database
          String url = "jdbc:oracle:thin:@localhost:1521:orcl";

          // Kết nối đến cơ sở dữ liệu
          Class.forName("oracle.jdbc.driver.OracleDriver");
          connection = DriverManager.getConnection(url, "sys as sysdba", "sys");
          System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
          System.out.println("Connection failed. Error: " + e.getMessage());
        }

        return connection;
    }
    private void btnVeTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVeTrangChuActionPerformed
        Form_Home home = new Form_Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVeTrangChuActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed

        try (Connection conn = connectToOracle()) {
            
            int updateResult;

          
            // Kiểm tra quyền UPDATE
            CallableStatement updateStmt = conn.prepareCall("{call Check_Update_Privilege(?, ?)}");
            updateStmt.setString(1, username1.toUpperCase());
            updateStmt.registerOutParameter(2, Types.INTEGER);
            updateStmt.execute();
            updateResult = updateStmt.getInt(2);

            // Xử lý kết quả
            if (updateResult == 1) {
                // Lấy thông tin từ giao diện người dùng
                String newFullName = txtFullName.getText();
                String newAddress = txtAddress.getText();
                String newPhone = txtPhone.getText();
                String newGmail = txtGmail.getText();

                // Câu lệnh SQL cập nhật thông tin người dùng
                String updateQuery = "UPDATE hotelbooking.user_info "
                                    + "SET fullname = ?, userAddress = ?, userPhone = ?, userEmail = ? "
                                    + "WHERE username = ?";

                try (Connection connection = DriverManager.getConnection(url, username, pass);
                     PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

                    // Đặt giá trị cho các tham số
                    preparedStatement.setString(1, newFullName);
                    preparedStatement.setString(2, newAddress);
                    preparedStatement.setString(3, newPhone);
                    preparedStatement.setString(4, newGmail);
                    preparedStatement.setString(5, username);

                    // Thực hiện câu lệnh cập nhật
                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        // Nếu có hàng được cập nhật, thông báo thành công
                        JOptionPane.showMessageDialog(this, "Thông tin đã được cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Nếu không có hàng nào được cập nhật, thông báo lỗi
                        JOptionPane.showMessageDialog(this, "Cập nhật thất bại. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Người dùng không có quyền SELECT và UPDATE
                JOptionPane.showMessageDialog(null, "Người dùng không có quyền UPDATE.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnProfileManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileManageActionPerformed
        // The SQL query with a placeholder for the username
        String sql = "SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE = ?";
        try (Connection connection = DriverManager.getConnection(url, username, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the username as a parameter to the prepared statement
            preparedStatement.setString(1, username.toUpperCase());

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // If there are results, show the profile management form
                    Form_ProfileForUser form = new Form_ProfileForUser();
                    form.show();
                } else {
                    // If no results, show a message indicating no management rights
                    JOptionPane.showMessageDialog(this, "Bạn chưa được cấp quyền Quản Lý Profile!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Bạn chưa được cấp quyền Quản Lý Profile!");
        }
    }//GEN-LAST:event_btnProfileManageActionPerformed

    private void BtnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCreateActionPerformed
        // Thực hiện kết nối đến cơ sở dữ liệu

            // Kiểm tra xem tệp QR Code đã tồn tại hay chưa
            String outputFile = "D:\\OracleHotelBooking\\" + username + ".png";
            File qrCodeFile = new File(outputFile);
            int fileSuffix = 1; // Số hậu tố cho tệp
            while (qrCodeFile.exists()) {
                // Nếu tệp đã tồn tại, thêm số duy nhất vào tên tệp
                outputFile = "D:\\OracleHotelBooking\\" + username + "_" + fileSuffix + ".png";
                qrCodeFile = new File(outputFile);
                fileSuffix++;
            }

            // Tiến hành tạo mã QR
            try {
                String data = username + "@" + pass;
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

                // Write to file image
                BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);
                ImageIO.write(image, "PNG", qrCodeFile);

                // Hiển thị thông báo thành công
                JOptionPane.showMessageDialog(this, "Đã tạo và lưu mã QR thành công tại: " + outputFile, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                // Hiển thị thông báo lỗi nếu có ngoại lệ xảy ra
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tạo mã QR", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_BtnCreateActionPerformed
    private void fillProfileInfor() {
        try {
            // Connect to the database
            Connection con = connectToOracle();
            String sql1 = "SELECT PROFILE FROM DBA_USERS WHERE USERNAME = '" + username.toUpperCase() + "'";
            PreparedStatement pst = con.prepareStatement(sql1);
            ResultSet rs = pst.executeQuery();
            System.out.println(username);
            // Process the result set and populate the list
            while (rs.next()) {
                String profileName = rs.getString("PROFILE");
                txtProfileName.setText(profileName);
                System.out.println(profileName);

                // Thực hiện truy vấn sql2 để lấy giá trị PASSWORD_REUSE_MAX
                String sql2 = "SELECT LIMIT FROM DBA_PROFILES WHERE PROFILE = ? AND RESOURCE_NAME = 'PASSWORD_REUSE_MAX'";
                PreparedStatement pst2 = con.prepareStatement(sql2);
                pst2.setString(1, profileName);
                ResultSet rs2 = pst2.executeQuery();

                // Xử lý kết quả của truy vấn sql2 và đặt giá trị vào txtPasswordFailureLimit
                if (rs2.next()) {
                    String passwordFailureLimit = rs2.getString("LIMIT");
                    txtPasswordFailureLimit.setText(passwordFailureLimit);
                }

                // Đóng tài nguyên của truy vấn sql2
                rs2.close();
                pst2.close();

                // Thực hiện truy vấn sql3 để lấy giá trị FAILED_LOGIN_ATTEMPTS
                String sql3 = "SELECT LIMIT FROM DBA_PROFILES WHERE PROFILE = ? AND RESOURCE_NAME = 'FAILED_LOGIN_ATTEMPTS'";
                PreparedStatement pst3 = con.prepareStatement(sql3);
                pst3.setString(1, profileName);
                ResultSet rs3 = pst3.executeQuery();

                // Xử lý kết quả của truy vấn sql3 và đặt giá trị vào txtIncorrectPassword
                if (rs3.next()) {
                    String incorrectPasswordLimit = rs3.getString("LIMIT");
                    txtIncorrectPassword.setText(incorrectPasswordLimit);
                }

                // Đóng tài nguyên của truy vấn sql3
                rs3.close();
                pst3.close();
            }

            // Đóng tài nguyên của truy vấn sql1
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void fillTable()
    {
        txtUserName.setText("");
        txtFullName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtGmail.setText("");

        String query = "select * from hotelbooking.user_info where username = '" + username + "'";
        try (Connection connection = DriverManager.getConnection(url, username, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String fullName = resultSet.getString("fullname");
                String address = resultSet.getString("userAddress");
                String phone = resultSet.getString("userPhone");
                String gmail = resultSet.getString("userEmail");
                
                txtUserName.setText(userName);
                txtFullName.setText(fullName);
                txtAddress.setText(address);
                txtPhone.setText(phone);
                txtGmail.setText(gmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCreate;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnProfileManage;
    private javax.swing.JButton btnVeTrangChu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JTextField txtIncorrectPassword;
    private javax.swing.JTextField txtPasswordFailureLimit;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtProfileName;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
