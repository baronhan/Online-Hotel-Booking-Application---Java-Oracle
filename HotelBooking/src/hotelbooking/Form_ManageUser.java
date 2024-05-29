package hotelbooking;
import hotelbooking.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import hotelbooking.Form_Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import javax.swing.DefaultComboBoxModel;

public class Form_ManageUser extends javax.swing.JFrame {
    private String username = hotelbooking.Form_Login.userName;
    private String password = hotelbooking.Form_Login.password;
    private List<User> list = new ArrayList<>();
    private DefaultTableModel tbModel;
    
    public Form_ManageUser() {
        initComponents();
        setLocationRelativeTo(null);
        initTable();
        initUser();
        fillComboBox();
    }
    private void fillTable()
    {
        while(tbModel.getRowCount() > 0)
            tbModel.removeRow(0);
        for(User user : list)
        {
            tbModel.addRow(new String[]{user.getUserID(), user.getUserName(), user.getFullname(), user.getUserEmail() ,user.getUserAddress(), user.getPhoneNumber()});
        }
        tbModel.fireTableDataChanged();
    }
    private void initTable()
    {
        tbModel = new DefaultTableModel();
        String[] columns = new String[]{"UserId", "UserName", "FullName", "UserEmail", "UserAddress", "UserPhone"};
        tbModel.setColumnIdentifiers(columns);
        
        tblUser.setModel(tbModel);
    }
    private void initUser()
    {
        try{
            // Connect to the database
            Connection con = connectToOracle();
            String sql = "SELECT * FROM hotelbooking.user_info";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            //Process the result set and populate the list
            while(rs.next()){
                String userId = rs.getString("UserId");
                String userName = rs.getString("UserName");
                String fullName = rs.getString("FullName");
                String userEmail = rs.getString("UserEmail");
                String userAddress = rs.getString("UserAddress");
                String userPhone = rs.getString("UserPhone");
                User user = new User(userId, userName, fullName, userEmail, userAddress, userPhone);
                list.add(user);
            }
            // Close resources
            rs.close();
            pst.close();
            con.close();   
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void fillComboBox() {
        Connection connection = connectToOracle();
        if (connection != null) {
            try {
                String sql = "SELECT DISTINCT PROFILE FROM DBA_PROFILES";
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                while (rs.next()) {
                    String profile = rs.getString("PROFILE");
                    model.addElement(profile);
                }

                cbProfile.setModel(model);

                rs.close();
                pst.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi thực hiện truy vấn. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Kết nối đến cơ sở dữ liệu không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtGmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        cbProfile = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnUpdateProfileForUser = new javax.swing.JButton();
        btnGrantProfileToUser = new javax.swing.JButton();
        btnRevokePermissions = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("USER MANAGEMENT");

        jLabel2.setText("UserName:");

        jLabel3.setText("FullName:");

        jLabel4.setText("Address:");

        jLabel5.setText("Phone:");

        jLabel6.setText("Gmail:");

        jLabel7.setText("Password:");

        jLabel8.setText("Confirm Password:");

        jLabel9.setText("Profile:");

        cbProfile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Functions"));

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update UserInfo");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnUpdateProfileForUser.setText("Update Profile For User");
        btnUpdateProfileForUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProfileForUserActionPerformed(evt);
            }
        });

        btnGrantProfileToUser.setText("Grant Permissions");
        btnGrantProfileToUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrantProfileToUserActionPerformed(evt);
            }
        });

        btnRevokePermissions.setText("Revoke Permissions");
        btnRevokePermissions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevokePermissionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnUpdateProfileForUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGrantProfileToUser, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnRevokePermissions, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnUpdate)
                        .addGap(29, 29, 29)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnRemove)
                    .addComponent(btnUpdate)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateProfileForUser)
                    .addComponent(btnGrantProfileToUser)
                    .addComponent(btnRevokePermissions))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(cbProfile, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGmail)
                            .addComponent(txtUserName)
                            .addComponent(txtFullName)
                            .addComponent(txtAddress)
                            .addComponent(txtPhone)
                            .addComponent(txtPassword)
                            .addComponent(txtConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(cbProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add User", jPanel1);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List Users", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        fillTable();
    }//GEN-LAST:event_formWindowOpened

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        int selected = tblUser.getSelectedRow();
        if(selected >= 0)
        {
            String username_click = (String) tbModel.getValueAt(selected, 0);
            for(User user : list)
            {
                if(user.getUserID().equals(username_click))
                {
                    txtUserName.setText(user.getUserName());
                    txtFullName.setText(user.getFullname());
                    txtAddress.setText(user.getUserAddress());
                    txtGmail.setText(user.getUserEmail());
                    txtPhone.setText(user.getPhoneNumber());
                    return;
                }
            }
        }
    }//GEN-LAST:event_tblUserMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtAddress.setText("");
        txtConfirmPassword.setText("");
        txtFullName.setText("");
        txtGmail.setText("");
        txtPassword.setText("");
        txtPhone.setText("");
        txtUserName.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        StringBuilder sb = new StringBuilder();
        Connection connection = connectToOracle();
        if(txtUserName.getText().equals(""))
        {
            sb.append("UserName is empty\n");
        }
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(connection != null)
        {
            try {
                for(User user : list)
                {
                    if(user.getUserName().equals(txtUserName.getText()))
                    {

                        //Chuẩn bị câu lệnh sp
                        String updateuser = "{call update_user_info_by_username(?, ?, ?, ?, ?)}";

                        //Chuẩn bị và thi hành câu lệnh sp
                        CallableStatement updateStatement = connection.prepareCall(updateuser);
                        updateStatement.setString(1, txtUserName.getText());
                        updateStatement.setString(2, txtFullName.getText());
                        updateStatement.setString(3, txtAddress.getText());
                        updateStatement.setString(4, txtPhone.getText());
                        updateStatement.setString(5, txtGmail.getText());
                        updateStatement.execute();
                        updateStatement.close();

                        fillTable();
                        JOptionPane.showMessageDialog(this, "User is updated");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "The user can't update");
            } catch(SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        StringBuilder sb = new StringBuilder();
        Connection connection = connectToOracle();
        if(txtUserName.getText().equals(""))
        {
            sb.append("UserName is empty\n");
        }
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(connection != null)
        {
            try{
                for(User user : list)
                {
                    if(user.getUserName().equals(txtUserName.getText()))
                    {
                        int choice = JOptionPane.showConfirmDialog(this, "Do you want to delete", "Confirm", JOptionPane.YES_NO_OPTION);
                        if(choice == JOptionPane.YES_OPTION)
                        {
                            //Chuẩn bị câu lệnh sp
                            String deleteuser = "{call delete_user_by_username(?)}";
                            //Chuẩn bị và thi hành câu lệnh sp
                            CallableStatement deleteStatement = connection.prepareCall(deleteuser);
                            deleteStatement.setString(1, txtUserName.getText());
                            deleteStatement.execute();
                            deleteStatement.close();

                            list.remove(user);
                            fillTable();
                            JOptionPane.showMessageDialog(this, "User is deleted");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "User not found");
            }catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        StringBuilder sb = new StringBuilder();
        Connection connection = connectToOracle();
        if(txtUserName.getText().equals(""))
        {
            sb.append("User is empty\n");
        }
        if(sb.length() > 0)
        {

            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(connection != null)
        {
            try {
                String username_ = txtUserName.getText();
                String fullname = txtFullName.getText();
                String address = txtAddress.getText();
                String email = txtGmail.getText();
                String phone = txtPhone.getText();
                String password_ = txtPassword.getText();
                String confirmpassword = txtConfirmPassword.getText();
                String profileString = (String) cbProfile.getSelectedItem();

                // Kiểm tra mật khẩu và xác nhận mật khẩu
                if (!password_.equals(confirmpassword)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu và xác nhận mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                 // Gọi function để lấy username được gợi ý từ cơ sở dữ liệu
                CallableStatement suggestionStatement = connection.prepareCall("{ ? = call get_suggested_username(?) }");
                suggestionStatement.registerOutParameter(1, Types.VARCHAR);
                suggestionStatement.setString(2, username_);
                suggestionStatement.execute();
                String suggestedUsername = suggestionStatement.getString(1);
                suggestionStatement.close();
                
                System.out.println(suggestedUsername);
                // Kiểm tra username đã tồn tại hay chưa
                CallableStatement checkStatement = connection.prepareCall("{ call check_username_exist(?, ?) }");
                checkStatement.setString(1, username_);
                checkStatement.registerOutParameter(2, Types.INTEGER);
                checkStatement.execute();
                int usernameExist = checkStatement.getInt(2);
                checkStatement.close();
                System.out.println(usernameExist);
                if (usernameExist > 0) {
                    // Nếu username đã tồn tại, hiển thị thông báo lỗi và xóa dữ liệu từ các textfield
                    JOptionPane.showMessageDialog(this, "Tên người dùng đã tồn tại! Hãy xem gợi ý từ hệ thống!");
                    txtUserName.setText("");
                    txtFullName.setText("");
                    txtAddress.setText("");
                    txtGmail.setText("");
                    txtPhone.setText("");
                    txtPassword.setText("");
                    txtConfirmPassword.setText("");

                    // Hiển thị username được gợi ý lên textfield
                    txtUserName.setText(suggestedUsername);
                } else {
                    // Chuẩn bị câu lệnh SQL để gọi stored procedure insert_user_info
                    String insertSql = "{call insert_user_info(?, ?, ?, ?, ?)}";

                    // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
                    CallableStatement insertStatement = connection.prepareCall(insertSql);
                    insertStatement.setString(1, username_);
                    insertStatement.setString(2, fullname);
                    insertStatement.setString(3, email);
                    insertStatement.setString(4, address);
                    insertStatement.setString(5, phone);
                    insertStatement.execute();
                    insertStatement.close();

                    // Chuẩn bị câu lệnh SQL để gọi stored procedure create_user_with_permissions
                    String createSql = "{call create_user_with_permissions(?, ?, ?)}";

                    // Chuẩn bị và thi hành lệnh gọi stored procedure create_user_with_permissions
                    CallableStatement createStatement = connection.prepareCall(createSql);
                    createStatement.setString(1, username_);
                    createStatement.setString(2, password_);
                    createStatement.setString(3, profileString);
                    createStatement.execute();
                    createStatement.close();


                    // Đóng kết nối và giải phóng tài nguyên
                    JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
                    fillTable();
                    btnResetActionPerformed(evt);
                }
                
            } catch (SQLException e) {
                
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(this, "Thêm mới người dùng không thành công");
            }
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateProfileForUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProfileForUserActionPerformed
        Connection connection = connectToOracle();
        
        if (connection != null) {
            try {
                // Lấy giá trị từ các trường văn bản
                String profileString = (String) cbProfile.getSelectedItem();
                String username_ = txtUserName.getText();

                // Chuẩn bị câu lệnh SQL để gọi stored procedure
                String updateProfileSql = "{call UPDATE_PROFILE_USER(?, ?)}";

                // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
                CallableStatement updateProfile = connection.prepareCall(updateProfileSql);
                updateProfile.setString(1, username_);
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
    }//GEN-LAST:event_btnUpdateProfileForUserActionPerformed

    private void btnGrantProfileToUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrantProfileToUserActionPerformed
        Connection connection = connectToOracle();
        
        if (connection != null) {
            try {
                // Lấy giá trị từ các trường văn bản
                String username_ = txtUserName.getText();

                // Chuẩn bị câu lệnh SQL để gọi stored procedure
                String grantPermissionsSql = "{call GRANT_PROFILE_PERMISSIONS(?)}";

                // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
                CallableStatement grantPermissions = connection.prepareCall(grantPermissionsSql);
                grantPermissions.setString(1, username_);
                grantPermissions.execute();
                grantPermissions.close();

                connection.close();

                JOptionPane.showMessageDialog(this, "Cấp quyền quản lý Profile cho User thành công!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Xử lý khi kết nối không thành công
            JOptionPane.showMessageDialog(this, "Kết nối đến cơ sở dữ liệu không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGrantProfileToUserActionPerformed

    private void btnRevokePermissionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevokePermissionsActionPerformed
        Connection connection = connectToOracle();
        
        if (connection != null) {
            try {
                // Lấy giá trị từ các trường văn bản
                String username_ = txtUserName.getText();

                // Chuẩn bị câu lệnh SQL để gọi stored procedure
                String revokePermissionsSql = "{call REVOKE_PROFILE_PERMISSIONS(?)}";

                // Chuẩn bị và thi hành lệnh gọi stored procedure insert_user_info
                CallableStatement revokePermissions = connection.prepareCall(revokePermissionsSql);
                revokePermissions.setString(1, username_);
                revokePermissions.execute();
                revokePermissions.close();

                connection.close();

                JOptionPane.showMessageDialog(this, "Thu hồi quản lý Profile cho User thành công!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Xử lý khi kết nối không thành công
            JOptionPane.showMessageDialog(this, "Kết nối đến cơ sở dữ liệu không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRevokePermissionsActionPerformed

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
            java.util.logging.Logger.getLogger(Form_ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_ManageUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnGrantProfileToUser;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnRevokePermissions;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateProfileForUser;
    private javax.swing.JComboBox<String> cbProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
