/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelbooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.UIManager;

/**
 *
 * @author LENOVO
 */
public class Form_GrantSystemPrivileges extends javax.swing.JFrame {
    private String username = hotelbooking.Form_Login.userName;
    private String password = hotelbooking.Form_Login.password;
    private String usernameIsClicked = "";
    private String privilegeIsClicked = "";
    private DefaultTableModel tbModel_User;
    private DefaultTableModel tbModel_Privilege;
    private DefaultTableModel tbModel_CheckPrivilege;
    public Form_GrantSystemPrivileges() {
        initComponents();
        initUserTable();
        initPrivilegeTable();
        initCheckPrivilegeTable();
    }

    private void fillTable()
    {
        Connection connection = connectToOracle(username, password);
        try{
            if(connection != null)
            {
                // Tạo câu truy vấn
                String query1 = "SELECT * FROM open_users_view";
                String query2 = "SELECT * FROM sys_privs_view";

                // Tạo đối tượng PreparedStatement để thực thi truy vấn
                PreparedStatement statement1 = connection.prepareStatement(query1);
                PreparedStatement statement2 = connection.prepareStatement(query2);
                
                // Thực thi truy vấn
                ResultSet resultSet1 = statement1.executeQuery();
                ResultSet resultSet2 = statement2.executeQuery();

                // Tạo DefaultTableModel để đẩy dữ liệu lên JTable
                DefaultTableModel model1 = (DefaultTableModel) tblUsers.getModel();
                DefaultTableModel model2 = (DefaultTableModel) tblSystemPrivileges.getModel();
                
                // Xóa tất cả các hàng cũ trong bảng
                model1.setRowCount(0);
                model2.setRowCount(0);

                // Đọc kết quả truy vấn và đẩy dữ liệu vào bảng
                while (resultSet1.next()) {
                    String username_ = resultSet1.getString("username");
                    String accountStatus = resultSet1.getString("account_status");

                    // Thêm một hàng mới vào bảng
                    model1.addRow(new Object[]{username_, accountStatus});
                }
                while (resultSet2.next()) {
                    String privilege = resultSet2.getString("PRIVILEGE");

                    // Thêm một hàng mới vào bảng
                    model2.addRow(new Object[]{privilege});
                }

                // Đóng tài nguyên
                resultSet1.close();
                statement1.close();
                resultSet2.close();
                statement2.close();
                connection.close();
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Lỗi thực thi câu lệnh. Error: " + e.getMessage());
        }
    }
    
    private void initUserTable()
    {
        tbModel_User = new DefaultTableModel();
        String[] columns = new String[]{"UserName", "Account_Status"};
        tbModel_User.setColumnIdentifiers(columns);
        
        tblUsers.setModel(tbModel_User);
    }
    
    private void initPrivilegeTable()
    {
        tbModel_Privilege = new DefaultTableModel();
        String[] columns = new String[]{"Privilege"};
        tbModel_Privilege.setColumnIdentifiers(columns);
        
        tblSystemPrivileges.setModel(tbModel_Privilege);
    }
    
    private void initCheckPrivilegeTable()
    {
        tbModel_CheckPrivilege = new DefaultTableModel();
        String[] columns = new String[]{"Grantee", "Privilege", "Admin_Option", "Common"};
        tbModel_CheckPrivilege.setColumnIdentifiers(columns);
        
        tblCheckPrivilege.setModel(tbModel_CheckPrivilege);
    }
    
    private Connection connectToOracle(String username, String password) {
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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSystemPrivileges = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        rdWithAdminOption = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        btnGrant = new javax.swing.JButton();
        btnRevoke = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCheckPrivilege = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("grant system privileges");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("List of Users"));

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsers);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("List of System Privileges"));

        tblSystemPrivileges.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSystemPrivileges.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSystemPrivilegesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSystemPrivileges);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        rdWithAdminOption.setText("With Admin Option");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(rdWithAdminOption)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(rdWithAdminOption)
                .addGap(23, 23, 23))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Functions"));

        btnGrant.setText("GRANT");
        btnGrant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrantActionPerformed(evt);
            }
        });

        btnRevoke.setText("REVOKE");
        btnRevoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevokeActionPerformed(evt);
            }
        });

        btnShow.setText("SHOW");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnGrant, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnRevoke, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrant)
                    .addComponent(btnRevoke)
                    .addComponent(btnShow))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Checking Privileges From User"));

        tblCheckPrivilege.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblCheckPrivilege);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        fillTable();
    }//GEN-LAST:event_formWindowOpened

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        int selected = tblUsers.getSelectedRow();
        if (selected >= 0) {
            usernameIsClicked = (String) tbModel_User.getValueAt(selected, 0);
            System.out.println(usernameIsClicked);
        }
    }//GEN-LAST:event_tblUsersMouseClicked

    private void tblSystemPrivilegesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSystemPrivilegesMouseClicked
        int selected = tblSystemPrivileges.getSelectedRow();
        if (selected >= 0) {
            privilegeIsClicked = (String) tbModel_Privilege.getValueAt(selected, 0);
            System.out.println(privilegeIsClicked);
        }
    }//GEN-LAST:event_tblSystemPrivilegesMouseClicked

    private void btnGrantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrantActionPerformed
        Connection connection = connectToOracle(username, password);
        if(usernameIsClicked.equals("") || privilegeIsClicked.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn UserName và Privilege!");
        }
        else
        {
            try{
                if(connection != null)
                {
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
                    int result = checkStatement_.getInt(3);
                    boolean isAdminOptionGranted = (result == 1);

                    if(isAdminOptionGranted && rdWithAdminOption.isSelected()) {
                        // Thông báo rằng quyền đã được gán với WITH ADMIN OPTION
                        JOptionPane.showMessageDialog(this, "Người dùng đã được gán quyền " + privilegeIsClicked + " với WITH ADMIN OPTION.");
                    } else {
                        if(rdWithAdminOption.isSelected())
                        {
                            String callStatement = "{call grantPrivilegeWithAdminOption(?, ?)}";
                            // Tạo đối tượng CallableStatement để gọi stored procedure
                            CallableStatement callableStatement = connection.prepareCall(callStatement);

                            // Đặt các tham số cho stored procedure
                            callableStatement.setString(1, usernameIsClicked);
                            callableStatement.setString(2, privilegeIsClicked);

                            // Thực thi stored procedure
                            callableStatement.execute();
                            JOptionPane.showMessageDialog(this, "Bạn đã gán quyền " + privilegeIsClicked + " cho người dùng " + usernameIsClicked + " với WITH ADMIN OPTION thành công!" );
                            rdWithAdminOption.setSelected(false);
                        } else {
                            String callStatement = "{call grantPrivilege(?, ?)}";
                            // Tạo đối tượng CallableStatement để gọi stored procedure
                            CallableStatement callableStatement = connection.prepareCall(callStatement);

                            // Đặt các tham số cho stored procedure
                            callableStatement.setString(1, usernameIsClicked);
                            callableStatement.setString(2, privilegeIsClicked);

                            // Thực thi stored procedure
                            callableStatement.execute();
                            JOptionPane.showMessageDialog(this, "Bạn đã gán quyền " + privilegeIsClicked + " cho người dùng " + usernameIsClicked + " thành công!" );
                            rdWithAdminOption.setSelected(false);
                        }
                         // Đóng kết nối
                        connection.close();
                    }
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "Lỗi thực thi câu lệnh. Error: " + e.getMessage());
            }
        }    
        // Đặt trạng thái mặc định cho RadioButton
        rdWithAdminOption.setSelected(false);

    }//GEN-LAST:event_btnGrantActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        String sql = "SELECT * FROM user_sys_privs_view WHERE grantee = ?";
        
        if(usernameIsClicked.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn UserName mà bạn muốn hiển thị!");
        }
        else
        {
            try (Connection connection = connectToOracle(username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
                preparedStatement.setString(1, usernameIsClicked); // Đặt giá trị của tham số

                DefaultTableModel model1 = (DefaultTableModel) tblCheckPrivilege.getModel();

                // Xóa tất cả các hàng cũ trong bảng
                model1.setRowCount(0);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Xử lý các dòng kết quả ở đây
                        String grantee = resultSet.getString("grantee");
                        String privilege = resultSet.getString("privilege");
                        String adminoption = resultSet.getString("admin_option");
                        String common = resultSet.getString("common");

                        model1.addRow(new Object[]{grantee, privilege, adminoption, common});
                    }
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu cần
            }
        }
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnRevokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevokeActionPerformed
        Connection connection = connectToOracle(username, password);
        if(usernameIsClicked.equals("") || privilegeIsClicked.equals(""))
        {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn vào UserName và Privilege!");
        }
        else
        {
            try{
                if(connection != null)
                {
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
                    int result = checkStatement_.getInt(3);
                    if(result == 1)
                    {
                        if(rdWithAdminOption.isSelected())
                        {
                            // Tạo đối tượng CallableStatement để gọi stored procedure
                            CallableStatement callableStatement = connection.prepareCall("{call REVOKE_WITH_ADMIN_OPTION(?, ?)}");

                            // Đặt các tham số cho stored procedure
                            callableStatement.setString(1, usernameIsClicked.toUpperCase());
                            callableStatement.setString(2, privilegeIsClicked);

                            // Thực thi stored procedure
                            callableStatement.execute();

                            JOptionPane.showMessageDialog(this, "Bạn đã thu hồi tùy chọn WITH ADMIN OPTION từ quyền " + privilegeIsClicked + " của người dùng " + usernameIsClicked + " thành công!" );
                        }else
                        {
                            String callStatement = "{call revokeSpecificPrivilege(?, ?)}";
                            // Tạo đối tượng CallableStatement để gọi stored procedure
                            CallableStatement callableStatement = connection.prepareCall(callStatement);

                            // Đặt các tham số cho stored procedure
                            callableStatement.setString(1, usernameIsClicked);
                            callableStatement.setString(2, privilegeIsClicked);

                            // Thực thi stored procedure
                            callableStatement.execute();
                            JOptionPane.showMessageDialog(this, "Bạn đã thu hồi quyền " + privilegeIsClicked + " của người dùng " + usernameIsClicked + "thành công!" );
                        }
                         // Đóng kết nối
                        connection.close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Người dùng không có quyền " + privilegeIsClicked);
                    }
                    
                }
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, "Lỗi thực thi câu lệnh. Error: " + e.getMessage());
            }
        }
        // Đặt trạng thái mặc định cho RadioButton
        rdWithAdminOption.setSelected(false);

    }//GEN-LAST:event_btnRevokeActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Form_GrantSystemPrivileges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_GrantSystemPrivileges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_GrantSystemPrivileges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_GrantSystemPrivileges.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_GrantSystemPrivileges().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrant;
    private javax.swing.JButton btnRevoke;
    private javax.swing.JButton btnShow;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable4;
    private javax.swing.JRadioButton rdWithAdminOption;
    private javax.swing.JTable tblCheckPrivilege;
    private javax.swing.JTable tblSystemPrivileges;
    private javax.swing.JTable tblUsers;
    // End of variables declaration//GEN-END:variables
}
