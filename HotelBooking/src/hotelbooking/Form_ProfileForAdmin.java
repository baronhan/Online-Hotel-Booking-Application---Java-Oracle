package hotelbooking;

import hotelbooking.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Form_ProfileForAdmin extends javax.swing.JFrame {
    private String username = hotelbooking.Form_Login.userName;
    private DefaultTableModel tbModel;
    
    public Form_ProfileForAdmin() {
        initComponents();
        initTable();
        fillTable();
    }
    
    private void initTable()
    {
        tbModel = new DefaultTableModel();
        String[] columns = new String[]{"PROFILE", "RESOURCE_NAME", "RESOURCE_TYPE", "LIMIT", "COMMON"};
        tbModel.setColumnIdentifiers(columns);
        
        tblProfile.setModel(tbModel);
    }
    private void fillTable()
    {
         while(tbModel.getRowCount() > 0)
            tbModel.removeRow(0);
         try{
            // Connect to the database
            Connection con = connectToOracle();
            String sql = "SELECT PROFILE, RESOURCE_NAME, RESOURCE_TYPE, LIMIT, COMMON  FROM DBA_PROFILES";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            //Process the result set and populate the list
            while(rs.next()){
                String profile = rs.getString("PROFILE");
                String resource_name = rs.getString("RESOURCE_NAME");
                String resource_type = rs.getString("RESOURCE_TYPE");
                String limit = rs.getString("LIMIT");
                String common = rs.getString("COMMON");
                
                Object[] rowData = {profile, resource_name, resource_type, limit, common};
                tbModel.addRow(rowData);
            }
             System.out.println("as");
            // Close resources
            rs.close();
            pst.close();
            con.close();   
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private Connection connectToOracle() {
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProfile = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenProfile = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtThoiGianSuDung = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoLanThayDoiPassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoLanDuocPhepSaiMatKhau = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThoiGianKhoa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSoSession = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnCreateProfile = new javax.swing.JButton();
        btnDeleteProfile = new javax.swing.JButton();
        btnRenew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách Profile"));

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
        jScrollPane2.setViewportView(tblProfile);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Profile"));

        jLabel1.setText("Tên profile");

        jLabel2.setText("Thời gian sử dụng");

        jLabel3.setText("Số lần thay đổi password");

        jLabel4.setText("Số lần được phép sai mật khẩu");

        jLabel5.setText("Thời gian khóa khi sai mật khẩu");

        jLabel6.setText("Số session cho User");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThoiGianSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLanThayDoiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSoLanDuocPhepSaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtThoiGianKhoa)
                        .addComponent(txtSoSession, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoLanDuocPhepSaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThoiGianSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtThoiGianKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6))
                    .addComponent(txtSoSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLanThayDoiPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Các chức năng"));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btnCreateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(btnDeleteProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRenew, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateProfile)
                    .addComponent(btnDeleteProfile)
                    .addComponent(btnRenew))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
        txtSoLanDuocPhepSaiMatKhau.setText("");
        txtSoLanThayDoiPassword.setText("");
        txtSoSession.setText("");
        txtTenProfile.setText("");
        txtThoiGianKhoa.setText("");
        txtThoiGianSuDung.setText("");
    }//GEN-LAST:event_btnRenewActionPerformed

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
                String createProfileSql = "{call CREATE_PROFILE(?, ?, ?, ?, ?, ?)}";

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
                String deleteProfileSql = "{call DELETE_PROFILE(?)}";

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

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_ProfileForAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateProfile;
    private javax.swing.JButton btnDeleteProfile;
    private javax.swing.JButton btnRenew;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProfile;
    private javax.swing.JTextField txtSoLanDuocPhepSaiMatKhau;
    private javax.swing.JTextField txtSoLanThayDoiPassword;
    private javax.swing.JTextField txtSoSession;
    private javax.swing.JTextField txtTenProfile;
    private javax.swing.JTextField txtThoiGianKhoa;
    private javax.swing.JTextField txtThoiGianSuDung;
    // End of variables declaration//GEN-END:variables
}
