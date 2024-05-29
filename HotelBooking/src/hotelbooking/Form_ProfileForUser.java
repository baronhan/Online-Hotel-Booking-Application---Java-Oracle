/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelbooking;

import hotelbooking.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class Form_ProfileForUser extends javax.swing.JFrame {
    private DefaultTableModel tbModel1;
    private DefaultTableModel tbModel2;
    private String username = hotelbooking.Form_Login.userName;
    private String pass = hotelbooking.Form_Login.password;
    private String usernameClickString;
    
    public Form_ProfileForUser() {
        initComponents();
        initTable();
        fillTable();
    }
    private void initTable()
    {
        //Profile Table
        tbModel1 = new DefaultTableModel();
        String[] columns1 = new String[]{"PROFILE"};
        tbModel1.setColumnIdentifiers(columns1);
        
        tblProfile.setModel(tbModel1);
        
        //User Table
        tbModel2 = new DefaultTableModel();
        String[] columns2 = new String[]{"USERNAME"};
        tbModel2.setColumnIdentifiers(columns2);
        
        tblUser.setModel(tbModel2);
    }
    private void fillTable() {
        while (tbModel1.getRowCount() > 0)
            tbModel1.removeRow(0);

        try {
            // Connect to the database
            Connection con = connectToOracle();

            // Thực hiện truy vấn cho bảng MY_PROFILES_VIEW
            String sqlMyProfilesView = "SELECT PROFILE FROM SYS.MY_PROFILES_VIEW";
            PreparedStatement pstMyProfilesView = con.prepareStatement(sqlMyProfilesView);
            ResultSet rsMyProfilesView = pstMyProfilesView.executeQuery();

            // Process the result set and populate the list
            while (rsMyProfilesView.next()) {
                String profile = rsMyProfilesView.getString("PROFILE");

                Object[] rowData = {profile};
                tbModel1.addRow(rowData);
            }

            // Đóng tài nguyên
            rsMyProfilesView.close();
            pstMyProfilesView.close();

            // Thực hiện truy vấn cho bảng user_info
            String sqlUserInfo = "SELECT username FROM hotelbooking.user_info";
            PreparedStatement pstUserInfo = con.prepareStatement(sqlUserInfo);
            ResultSet rsUserInfo = pstUserInfo.executeQuery();

            // Process the result set and populate the list
            while (rsUserInfo.next()) {
                String username = rsUserInfo.getString("USERNAME");

                // Đối với bảng user_info, bạn có thể chỉ cần một cột username
                Object[] rowData = {username};
                tbModel2.addRow(rowData);
            }

            // Đóng tài nguyên
            rsUserInfo.close();
            pstUserInfo.close();

            // Đóng kết nối
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProfile = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenProfile = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtThoiGianSuDung = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoLanThayDoiPassword = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLanDuocPhepSaiMatKhau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtThoiGianKhoa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoSession = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnCreateProfile = new javax.swing.JButton();
        btnDeleteProfile = new javax.swing.JButton();
        btnRenew = new javax.swing.JButton();
        btnCapNhatProfile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("PROFILE FOR USER");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách Users"));

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách Profiles"));

        tblProfile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProfileMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProfile);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Profile"));

        jLabel2.setText("Tên profile");

        jLabel3.setText("Thời gian sử dụng");

        jLabel4.setText("Số lần thay đổi password");

        jLabel5.setText("Số lần được phép sai mật khẩu");

        jLabel6.setText("Thời gian khóa khi sai mật khẩu");

        jLabel7.setText("Số session cho User");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52))
                            .addComponent(jLabel3))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtThoiGianSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLanThayDoiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoLanDuocPhepSaiMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(txtThoiGianKhoa)
                    .addComponent(txtSoSession))
                .addGap(41, 41, 41))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoLanDuocPhepSaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtThoiGianSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtThoiGianKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoLanThayDoiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtSoSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Các chức năng"));

        btnCreateProfile.setText("Tạo Profile");
        btnCreateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateProfileActionPerformed(evt);
            }
        });

        btnDeleteProfile.setText("Xóa Profile");
        btnDeleteProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProfileActionPerformed(evt);
            }
        });

        btnRenew.setText("Làm Mới");
        btnRenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenewActionPerformed(evt);
            }
        });

        btnCapNhatProfile.setText("Cập nhật Profile");
        btnCapNhatProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnCreateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(btnDeleteProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(btnCapNhatProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRenew, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateProfile)
                    .addComponent(btnDeleteProfile)
                    .addComponent(btnRenew)
                    .addComponent(btnCapNhatProfile))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(334, 334, 334))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateProfileActionPerformed
        Connection connection = connectToOracle();
        if (connection != null) {
            try {
                // Lấy giá trị từ các trường văn bản
                String profileName = txtTenProfile.getText();
                int session = Integer.parseInt(txtSoSession.getText());
                int connectionTime = Integer.parseInt(txtThoiGianSuDung.getText());
                int reUsePass = Integer.parseInt(txtSoLanThayDoiPassword.getText());
                int failLogin = Integer.parseInt(txtSoLanDuocPhepSaiMatKhau.getText());
                int passLock = Integer.parseInt(txtThoiGianKhoa.getText());

                // Chuẩn bị câu lệnh SQL để gọi stored procedure
                String createProfileSql = "{call SYS.CREATE_PROFILE(?, ?, ?, ?, ?, ?)}";

                // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
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

                JOptionPane.showMessageDialog(this, "Tạo Profile thành công!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Xử lý khi kết nối không thành công
            JOptionPane.showMessageDialog(this, "Kết nối đến cơ sở dữ liệu không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        fillTable();
    }//GEN-LAST:event_btnCreateProfileActionPerformed

    private void btnDeleteProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProfileActionPerformed
        Connection connection = connectToOracle();
        if (connection != null) {
            try {
                // Lấy giá trị từ các trường văn bản
                String profileName = txtTenProfile.getText();

                // Chuẩn bị câu lệnh SQL để gọi stored procedure
                String deleteProfileSql = "{call SYS.DELETE_PROFILE(?)}";

                // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
                CallableStatement deleteProfile = connection.prepareCall(deleteProfileSql);
                deleteProfile.setString(1, profileName);
                deleteProfile.execute();
                deleteProfile.close();

                connection.close();

                JOptionPane.showMessageDialog(this, "Xóa Profile thành công!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Xử lý khi kết nối không thành công
            JOptionPane.showMessageDialog(this, "Kết nối đến cơ sở dữ liệu không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        fillTable();
    }//GEN-LAST:event_btnDeleteProfileActionPerformed

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
        txtSoLanDuocPhepSaiMatKhau.setText("");
        txtSoLanThayDoiPassword.setText("");
        txtSoSession.setText("");
        txtTenProfile.setText("");
        txtThoiGianKhoa.setText("");
        txtThoiGianSuDung.setText("");
    }//GEN-LAST:event_btnRenewActionPerformed

    private void tblProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProfileMouseClicked
        int selected = tblProfile.getSelectedRow();
        if(selected >= 0)
        {
            String profilename_click = (String) tbModel1.getValueAt(selected, 0);
            txtTenProfile.setText(profilename_click);
        }
    }//GEN-LAST:event_tblProfileMouseClicked

    private void btnCapNhatProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatProfileActionPerformed
        Connection connection = connectToOracle();
        
        if (connection != null) {
            try {
                // Lấy giá trị từ các trường văn bản
                String profileString = txtTenProfile.getText();

                // Chuẩn bị câu lệnh SQL để gọi stored procedure
                String updateProfileSql = "{call SYS.UPDATE_PROFILE_USER(?, ?)}";

                // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
                CallableStatement updateProfile = connection.prepareCall(updateProfileSql);
                updateProfile.setString(1, usernameClickString);
                updateProfile.setString(2, profileString);
                updateProfile.execute();
                updateProfile.close();

                connection.close();

                JOptionPane.showMessageDialog(this, "Cập nhật Profile thành công!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Xử lý khi kết nối không thành công
            JOptionPane.showMessageDialog(this, "Kết nối đến cơ sở dữ liệu không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCapNhatProfileActionPerformed

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        int selected = tblUser.getSelectedRow();
        if(selected >= 0)
        {
            String profilename_click = (String) tbModel2.getValueAt(selected, 0);
            usernameClickString = profilename_click;
        }
    }//GEN-LAST:event_tblUserMouseClicked

    private Connection connectToOracle() {
        Connection connection = null;
        try {
            // Xây dựng URL kết nối dựa trên database
            String url = "jdbc:oracle:thin:@:1521:orcl"; // Thay localhost và port nếu cần

            // Kết nối đến cơ sở dữ liệu
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, pass);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed. Error: " + e.getMessage());
        }
        return connection;
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
            java.util.logging.Logger.getLogger(Form_ProfileForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_ProfileForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_ProfileForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_ProfileForUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_ProfileForUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatProfile;
    private javax.swing.JButton btnCreateProfile;
    private javax.swing.JButton btnDeleteProfile;
    private javax.swing.JButton btnRenew;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProfile;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtSoLanDuocPhepSaiMatKhau;
    private javax.swing.JTextField txtSoLanThayDoiPassword;
    private javax.swing.JTextField txtSoSession;
    private javax.swing.JTextField txtTenProfile;
    private javax.swing.JTextField txtThoiGianKhoa;
    private javax.swing.JTextField txtThoiGianSuDung;
    // End of variables declaration//GEN-END:variables
}
