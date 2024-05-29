package hotelbooking;




import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.awt.Color;
import javax.swing.JButton;
/**
 *
 * @author nguye
 */
public class Form_GrantObjectPrivilege extends javax.swing.JFrame {
    
    
    private String username = hotelbooking.Form_Login.userName;
    private String password = hotelbooking.Form_Login.password;
    private String[] usersArray;
  

//    private void checkPermissions(String user, String table) {
//        try {
//            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//            Connection con = DriverManager.getConnection(url, this.username, password);
//            String sql = "SELECT grantee, privilege FROM dba_tab_privs WHERE grantee = ? AND table_name = ?";
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, user);
//            pst.setString(2, table);
//            ResultSet rs = pst.executeQuery();
//
//            boolean hasSelect = false;
//            boolean hasInsert = false;
//            boolean hasUpdateDelete = false;
//
//            while (rs.next()) {
//                String privilege = rs.getString("privilege");
//                if (privilege.equals("SELECT")) {
//                    hasSelect = true;
//                } else if (privilege.equals("INSERT")) {
//                    hasInsert = true;
//                } else if (privilege.equals("UPDATE") || privilege.equals("DELETE")) {
//                    hasUpdateDelete = true;
//                }
//            }
//
//            Grant_Select.setBackground(hasSelect ? Color.YELLOW : Color.GREEN);
//            Grant_Update.setBackground(hasUpdateDelete ? Color.YELLOW : Color.GREEN);
//            Grant_Delete.setBackground(hasUpdateDelete ? Color.YELLOW : Color.GREEN);
//
//            con.close();
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
    public Form_GrantObjectPrivilege() {
        initComponents();
        this.setTitle("Giam Sat");
        this.setLocationRelativeTo(null);
        showData();
        combox();
     
    }
  private void combox() {
    try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            Connection con = DriverManager.getConnection(url, this.username, this.password);

            String sql = "SELECT username FROM dba_users ORDER BY username";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            ArrayList<String> usersList = new ArrayList<>();

            while (rs.next()) {
                String username_ = rs.getString("username");
                usersList.add(username_);
            }

            // Lưu trữ dữ liệu vào biến instance
            usersArray = usersList.toArray(new String[0]);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(usersArray);
            cpuser.setModel(model);

            con.close();
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
}

    private void showData() {
        DefaultTableModel tbModel = (DefaultTableModel) user.getModel();
        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", username, password);
             PreparedStatement pst = con.prepareStatement("SELECT username, account_status FROM dba_users WHERE account_status = 'OPEN'");
             ResultSet rs = pst.executeQuery()) {

            // Xóa các dòng cũ trong bảng
            tbModel.setRowCount(0);

            boolean hasRecords = false; // Biến đánh dấu có bản ghi nào hay không
            while (rs.next()) {
                hasRecords = true; // Đã có ít nhất một bản ghi được tìm thấy
                String username_ = rs.getString("username");
                String accountStatus = rs.getString("account_status");

                // Thêm dữ liệu vào model
                tbModel.addRow(new Object[]{username_, accountStatus});
            }

            // Load dữ liệu cho bảng table_name
            loadDataForSelectedUser();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        user = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_ten_user = new javax.swing.JTextField();
        cpuser = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        rdWithGrantOption = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_name = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_user_can_thao_tac_den_bang = new javax.swing.JTextField();
        txt_table = new javax.swing.JTextField();
        Grant_Select = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        Grant_Update = new javax.swing.JButton();
        Grant_Delete = new javax.swing.JButton();
        KiemTraCoQuyenDoChua = new javax.swing.JButton();
        rd_Select = new javax.swing.JRadioButton();
        rd_Update = new javax.swing.JRadioButton();
        rd_Delete = new javax.swing.JRadioButton();
        rd_Insert = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        Btn_Thu_Hoi = new javax.swing.JButton();
        BtnThuHoi1Quyen = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("List of User"));

        user.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "USERNAME", "ACCOUNT_STATUS"
            }
        ));
        user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(user);

        jLabel1.setText("Schema:");

        cpuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpuserActionPerformed(evt);
            }
        });

        jLabel6.setText("With Grant Option: ");

        rdWithGrantOption.setText("With Grant Option");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cpuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ten_user, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdWithGrantOption))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_ten_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cpuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdWithGrantOption))
                .addGap(36, 36, 36))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("List of Table"));

        table_name.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        table_name.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TABLE_NAME"
            }
        ));
        table_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_nameMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_name);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));
        jPanel3.setToolTipText("");

        jLabel2.setText("User Phân Quyền:");

        jLabel3.setText("Table");

        Grant_Select.setText("Select");
        Grant_Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Grant_SelectActionPerformed(evt);
            }
        });

        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        Grant_Update.setText("Update");
        Grant_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Grant_UpdateActionPerformed(evt);
            }
        });

        Grant_Delete.setText("Delete");
        Grant_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Grant_DeleteActionPerformed(evt);
            }
        });

        KiemTraCoQuyenDoChua.setText("Xem");
        KiemTraCoQuyenDoChua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KiemTraCoQuyenDoChuaActionPerformed(evt);
            }
        });

        rd_Select.setText("Select");

        rd_Update.setText("Update");

        rd_Delete.setText("Delete");

        rd_Insert.setText("Insert");

        jLabel4.setText("Thu Hồi Quyền:");

        Btn_Thu_Hoi.setText("Thu Hồi ALL");
        Btn_Thu_Hoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Thu_HoiActionPerformed(evt);
            }
        });

        BtnThuHoi1Quyen.setText("Thu Hồi 1 Quyền");
        BtnThuHoi1Quyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnThuHoi1QuyenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_user_can_thao_tac_den_bang, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_table, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rd_Select, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rd_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rd_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rd_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Grant_Select, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(KiemTraCoQuyenDoChua, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Btn_Thu_Hoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnThuHoi1Quyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Grant_Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(Grant_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Grant_Select, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Grant_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Grant_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_user_can_thao_tac_den_bang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rd_Select)
                            .addComponent(rd_Update)
                            .addComponent(rd_Delete)
                            .addComponent(rd_Insert)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtnThuHoi1Quyen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(KiemTraCoQuyenDoChua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Thu_Hoi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 102));

        jLabel5.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("grant permissions");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(301, 301, 301))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
          

 

// TODO add your handling code here:
private void loadDataForSelectedUser() {
        String selectedUser = (String) cpuser.getSelectedItem();
        if (selectedUser == null || selectedUser.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một user.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel tbModel = (DefaultTableModel) table_name.getModel();
        tbModel.setRowCount(0);

        try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            Connection con = DriverManager.getConnection(url, this.username, this.password);
            String sql = "SELECT table_name FROM all_tables WHERE owner = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, selectedUser);
            ResultSet rs = pst.executeQuery();

            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                String tableName = rs.getString("table_name");
                tbModel.addRow(new Object[]{tableName});
            }
            txt_ten_user.setText(selectedUser);
            con.close();

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
}

    private void Grant_SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Grant_SelectActionPerformed
        
        String ten_user = txt_ten_user.getText();
        String ten_user_can_thao_tac_den_bang = txt_user_can_thao_tac_den_bang.getText();
        String ten_bang = txt_table.getText();

        try(Connection connection = connectToOracle(username, password);
            CallableStatement statement = connection.prepareCall("{call CheckUserTablePrivilege(?,?,?,?)}"))
                {
                    //Thiết lập các tham số đầu vào
                    statement.setString(1, ten_user_can_thao_tac_den_bang);
                    statement.setString(2, "SELECT");
                    statement.setString(3, ten_bang);
                    //Đăng ký tham số đầu ra
                    statement.registerOutParameter(4, Types.INTEGER);
                    //Thực thi storedprocedure
                    statement.execute();
                    //Lấy giá trị của tham số đầu ra
                    int result = statement.getInt(4);
                    if(result == 0){
                        if(rdWithGrantOption.isSelected())
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectstatement = connection.prepareCall("{call GrantSelectWGOToUser(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền SELECT với tùy chọn WITH GRANT OPTION đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                        else
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectWGOstatement = connection.prepareCall("{call GrantSelectToUser(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectWGOstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectWGOstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectWGOstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền SELECT đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Người dùng " + ten_user_can_thao_tac_den_bang + " đã được cấp quyền SELECT!");
                    }
                }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }          
    }//GEN-LAST:event_Grant_SelectActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        String ten_user = txt_ten_user.getText();
        String ten_user_can_thao_tac_den_bang = txt_user_can_thao_tac_den_bang.getText();
        String ten_bang = txt_table.getText();

        try(Connection connection = connectToOracle(username, password);
            CallableStatement statement = connection.prepareCall("{call CheckUserTablePrivilege(?,?,?,?)}"))
                {
                    //Thiết lập các tham số đầu vào
                    statement.setString(1, ten_user_can_thao_tac_den_bang);
                    statement.setString(2, "INSERT");
                    statement.setString(3, ten_bang);
                    //Đăng ký tham số đầu ra
                    statement.registerOutParameter(4, Types.INTEGER);
                    //Thực thi storedprocedure
                    statement.execute();
                    //Lấy giá trị của tham số đầu ra
                    int result = statement.getInt(4);
                    if(result == 0){
                        if(rdWithGrantOption.isSelected())
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectstatement = connection.prepareCall("{call GrantInsertToUserWithGrant(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền UPDATE với tùy chọn WITH GRANT OPTION đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                        else
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectWGOstatement = connection.prepareCall("{call GrantInsertToUser(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectWGOstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectWGOstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectWGOstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền INSERT đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Người dùng " + ten_user_can_thao_tac_den_bang + " đã được cấp quyền SELECT!");
                    }
                }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }          
    }//GEN-LAST:event_btnInsertActionPerformed

    private void Grant_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Grant_UpdateActionPerformed
    
        String ten_user = txt_ten_user.getText();
        String ten_user_can_thao_tac_den_bang = txt_user_can_thao_tac_den_bang.getText();
        String ten_bang = txt_table.getText();

        try(Connection connection = connectToOracle(username, password);
            CallableStatement statement = connection.prepareCall("{call CheckUserTablePrivilege(?,?,?,?)}"))
                {
                    //Thiết lập các tham số đầu vào
                    statement.setString(1, ten_user_can_thao_tac_den_bang);
                    statement.setString(2, "INSERT");
                    statement.setString(3, ten_bang);
                    //Đăng ký tham số đầu ra
                    statement.registerOutParameter(4, Types.INTEGER);
                    //Thực thi storedprocedure
                    statement.execute();
                    //Lấy giá trị của tham số đầu ra
                    int result = statement.getInt(4);
                    if(result == 0){
                        if(rdWithGrantOption.isSelected())
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectstatement = connection.prepareCall("{call GrantUpdateToUserWGO(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền UPDATE với tùy chọn WITH GRANT OPTION đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                        else
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectWGOstatement = connection.prepareCall("{call GrantUpdateToUser(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectWGOstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectWGOstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectWGOstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền UPDATE đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Người dùng " + ten_user_can_thao_tac_den_bang + " đã được cấp quyền SELECT!");
                    }
                }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }          
    }//GEN-LAST:event_Grant_UpdateActionPerformed

    private void Grant_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Grant_DeleteActionPerformed
    
        String ten_user = txt_ten_user.getText();
        String ten_user_can_thao_tac_den_bang = txt_user_can_thao_tac_den_bang.getText();
        String ten_bang = txt_table.getText();

        try(Connection connection = connectToOracle(username, password);
            CallableStatement statement = connection.prepareCall("{call CheckUserTablePrivilege(?,?,?,?)}"))
                {
                    //Thiết lập các tham số đầu vào
                    statement.setString(1, ten_user_can_thao_tac_den_bang);
                    statement.setString(2, "INSERT");
                    statement.setString(3, ten_bang);
                    //Đăng ký tham số đầu ra
                    statement.registerOutParameter(4, Types.INTEGER);
                    //Thực thi storedprocedure
                    statement.execute();
                    //Lấy giá trị của tham số đầu ra
                    int result = statement.getInt(4);
                    if(result == 0){
                        if(rdWithGrantOption.isSelected())
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectstatement = connection.prepareCall("{call GrantDeleteToUserWGO(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền DELETE với tùy chọn WITH GRANT OPTION đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                        else
                        {
                            //Chuẩn bị câu lệnh thực thi
                            CallableStatement GrantSelectWGOstatement = connection.prepareCall("{call GrantDeleteToUser(?,?)}");
                            //Thiết lập các tham số đầu vào
                            GrantSelectWGOstatement.setString(1, ten_user + "." + ten_bang);
                            GrantSelectWGOstatement.setString(2, ten_user_can_thao_tac_den_bang);
                            //Thực thi
                            GrantSelectWGOstatement.execute();
                            connection.close();
                            JOptionPane.showMessageDialog(this, "Quyền DELETE đã được cấp cho người dùng " + ten_user_can_thao_tac_den_bang +
                            " trên bảng " + ten_bang + " của người dùng " + ten_user + ".");
                            rdWithGrantOption.setSelected(false);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Người dùng " + ten_user_can_thao_tac_den_bang + " đã được cấp quyền SELECT!");
                    }
                }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }          
        
    }//GEN-LAST:event_Grant_DeleteActionPerformed

    private void userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMouseClicked
        // Lấy chỉ mục của hàng được chọn
    int selectedRow = user.getSelectedRow();
    
    // Kiểm tra nếu có hàng được chọn
    if (selectedRow != -1) {
        // Lấy dữ liệu từ cột "USERNAME" của hàng được chọn
        String username = user.getValueAt(selectedRow, 0).toString();
        
        // Gán tên người dùng vào trường văn bản txt_ten_user
        
        txt_user_can_thao_tac_den_bang.setText(username);
    }
    }//GEN-LAST:event_userMouseClicked

    private void table_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_nameMouseClicked
         // Lấy chỉ mục của hàng được chọn
    int selectedRow = table_name.getSelectedRow();
    
    // Kiểm tra nếu có hàng được chọn
    if (selectedRow != -1) {
        // Lấy dữ liệu từ cột "USERNAME" của hàng được chọn
        String table_na = table_name.getValueAt(selectedRow, 0).toString();
        txt_table.setText(table_na);
       
    }
    }//GEN-LAST:event_table_nameMouseClicked

    private void KiemTraCoQuyenDoChuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KiemTraCoQuyenDoChuaActionPerformed
        // Get the user and table names from text fields
        String ten_user_can_thao_tac_den_bang = txt_user_can_thao_tac_den_bang.getText().trim();
        String ten_bang = txt_table.getText().trim();

        // Kiểm tra xem các trường có rỗng không và xử lý tương ứng
        if (ten_user_can_thao_tac_den_bang.isEmpty() && ten_bang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (ten_user_can_thao_tac_den_bang.isEmpty() && !ten_bang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên người dùng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (!ten_user_can_thao_tac_den_bang.isEmpty() && ten_bang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên bảng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                Connection con = DriverManager.getConnection(url, this.username, password);

                // Thực hiện câu truy vấn SQL để kiểm tra quyền
                String sql = "SELECT privilege FROM dba_tab_privs WHERE grantee = ? AND table_name = ? AND (privilege = 'SELECT' OR privilege = 'INSERT' OR privilege = 'UPDATE' OR privilege = 'DELETE')";


                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, ten_user_can_thao_tac_den_bang.toUpperCase()); // Chuyển tên người dùng thành chữ in hoa
                pst.setString(2, ten_bang.toUpperCase()); // Chuyển tên bảng thành chữ in hoa

                ResultSet rs = pst.executeQuery();

                boolean hasSelect = false;
                boolean hasInsert = false;
                boolean hasUpdate = false;
                boolean hasDelete = false;

                while (rs.next()) {
                    String privilege = rs.getString("PRIVILEGE");

                    if (privilege.equals("SELECT")) {
                        hasSelect = true;
                    }
                    if (privilege.equals("INSERT")) {
                        hasInsert = true;
                    }
                    if (privilege.equals("UPDATE")) {
                        hasUpdate = true;
                    }
                    if (privilege.equals("DELETE")) {
                        hasDelete = true;
                    }
                }
                
                    if (hasSelect && hasUpdate && hasInsert && hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng có tất cả quyền SELECT, INSERT, UPDATE và DELETE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.YELLOW);
                    btnInsert.setBackground(Color.YELLOW);
                    Grant_Update.setBackground(Color.YELLOW);
                    Grant_Delete.setBackground(Color.YELLOW);
                } else if (hasSelect && hasInsert && !hasUpdate && !hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng có quyền SELECT và INSERT đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.YELLOW);
                    btnInsert.setBackground(Color.YELLOW);
                    Grant_Update.setBackground(Color.GREEN);
                    Grant_Delete.setBackground(Color.GREEN);
                } else if (hasSelect && hasUpdate && !hasInsert && !hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng có quyền SELECT và UPDATE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.YELLOW);
                    btnInsert.setBackground(Color.GREEN);
                    Grant_Update.setBackground(Color.YELLOW);
                    Grant_Delete.setBackground(Color.GREEN);
                } else if (hasSelect && hasDelete && !hasInsert && !hasUpdate) {
                    JOptionPane.showMessageDialog(this, "Người dùng có quyền SELECT và DELETE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.YELLOW);
                    btnInsert.setBackground(Color.GREEN);
                    Grant_Update.setBackground(Color.GREEN);
                    Grant_Delete.setBackground(Color.YELLOW);
                } else if (hasInsert && hasUpdate && !hasSelect && !hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng có quyền INSERT và UPDATE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.GREEN);
                    btnInsert.setBackground(Color.YELLOW);
                    Grant_Update.setBackground(Color.YELLOW);
                    Grant_Delete.setBackground(Color.GREEN);
                } else if (hasInsert && hasDelete && !hasSelect && !hasUpdate) {
                    JOptionPane.showMessageDialog(this, "Người dùng có quyền INSERT và DELETE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.GREEN);
                    btnInsert.setBackground(Color.YELLOW);
                    Grant_Update.setBackground(Color.GREEN);
                    Grant_Delete.setBackground(Color.YELLOW);
                } else if (hasUpdate && hasDelete && !hasSelect && !hasInsert) {
                    JOptionPane.showMessageDialog(this, "Người dùng có quyền UPDATE và DELETE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.GREEN);
                    btnInsert.setBackground(Color.GREEN);
                    Grant_Update.setBackground(Color.YELLOW);
                    Grant_Delete.setBackground(Color.YELLOW);
                } else if (hasSelect && !hasInsert && !hasUpdate && !hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng chỉ có quyền SELECT đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.YELLOW);
                    btnInsert.setBackground(Color.GREEN);
                    Grant_Update.setBackground(Color.GREEN);
                    Grant_Delete.setBackground(Color.GREEN);
                } else if (hasInsert && !hasSelect && !hasUpdate && !hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng chỉ có quyền INSERT đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.GREEN);
                    btnInsert.setBackground(Color.YELLOW);
                    Grant_Update.setBackground(Color.GREEN);
                    Grant_Delete.setBackground(Color.GREEN);
                } else if (hasUpdate && !hasSelect && !hasInsert && !hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng chỉ có quyền UPDATE với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.GREEN);
                    btnInsert.setBackground(Color.GREEN);
                    Grant_Update.setBackground(Color.YELLOW);
                    Grant_Delete.setBackground(Color.GREEN);
                } else if (hasDelete && !hasSelect && !hasInsert && !hasUpdate) {
                    JOptionPane.showMessageDialog(this, "Người dùng chỉ có quyền DELETE với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.GREEN);
                    btnInsert.setBackground(Color.GREEN);
                    Grant_Update.setBackground(Color.GREEN);
                    Grant_Delete.setBackground(Color.YELLOW);
                }
                else if (hasSelect && hasInsert && hasUpdate && !hasDelete) {
                    JOptionPane.showMessageDialog(this, "Người dùng có quyền SELECT, INSERT và UPDATE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.YELLOW);
                    btnInsert.setBackground(Color.YELLOW);
                    Grant_Update.setBackground(Color.YELLOW);
                    Grant_Delete.setBackground(Color.GREEN);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Người dùng không có quyền SELECT, INSERT, UPDATE hoặc DELETE đối với bảng này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Grant_Select.setBackground(Color.GREEN);
                    btnInsert.setBackground(Color.GREEN);
                    Grant_Update.setBackground(Color.GREEN);
                    Grant_Delete.setBackground(Color.GREEN);
                }


                con.close();
            } catch (SQLException e) {
                System.out.println("Lỗi: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
    }

        

    }//GEN-LAST:event_KiemTraCoQuyenDoChuaActionPerformed

    private void Btn_Thu_HoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Thu_HoiActionPerformed
       kiemTraQuyen(); 
       thuHoiQuyen();
    }//GEN-LAST:event_Btn_Thu_HoiActionPerformed

    private void cpuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpuserActionPerformed
        loadDataForSelectedUser();
    }//GEN-LAST:event_cpuserActionPerformed

    private void BtnThuHoi1QuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThuHoi1QuyenActionPerformed
        kiemTraQuyen();
        thuHoi1Quyen();
    }//GEN-LAST:event_BtnThuHoi1QuyenActionPerformed
    private void thuHoi1Quyen() {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            Connection con = DriverManager.getConnection(url, this.username, password);
            
            String schema = txt_ten_user.getText();
            String user = txt_user_can_thao_tac_den_bang.getText();
            String tableName = txt_table.getText();
            String privilege = ""; // Biến để lưu trữ quyền được chọn
            
            
            // Xác định quyền được chọn dựa trên RadioButton
            if (rd_Select.isSelected()) {
                privilege = "SELECT";
            } else if (rd_Insert.isSelected()) {
                privilege = "INSERT";
            } else if (rd_Update.isSelected()) {
                privilege = "UPDATE";
            } else if (rd_Delete.isSelected()) {
                privilege = "DELETE";
            }
            
            if(!rd_Select.isSelected() && !rd_Delete.isSelected() && !rd_Update.isSelected() && !rd_Insert.isSelected())
            {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn vào 1 quyền bạn muốn thu hồi");
            }
            else
            {
                // Kiểm tra xem người dùng đã được gán quyền với tùy chọn "WITH GRANT OPTION" hay không
                CallableStatement checkStmt = con.prepareCall("{call CHECK_WITH_GRANT_OPTION(?, ?, ?, ?)}");
                checkStmt.setString(1, user.toUpperCase());
                checkStmt.setString(2, tableName.toUpperCase());
                checkStmt.setString(3, privilege.toUpperCase());
                checkStmt.registerOutParameter(4, Types.NUMERIC);
                checkStmt.execute();

                int withGrantOption = checkStmt.getInt(4);

                // Nếu người dùng đã được gán quyền với tùy chọn "WITH GRANT OPTION", tiếp tục thu hồi quyền
                if (rdWithGrantOption.isSelected() && withGrantOption == 1) {
                    // Gọi Stored Procedure để thu hồi quyền với tùy chọn "WITH GRANT OPTION"
                    CallableStatement cstmt = con.prepareCall("{call REVOKE_PRIVILEGE_WGO(?, ?, ?)}");
                    cstmt.setString(1, user.toUpperCase());
                    cstmt.setString(2, (schema + "." +tableName).toUpperCase());
                    cstmt.setString(3, privilege.toUpperCase());
                    System.out.println(user);
                    System.out.println((schema + "." +tableName));
                    System.out.println(privilege);
                    cstmt.execute();
                    // Hiển thị thông báo khi thực hiện thành công
                    JOptionPane.showMessageDialog(this, "Thu hồi quyền với tùy chọn WITH GRANT OPTION thành công cho người dùng " + user + "!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }else if(rdWithGrantOption.isSelected() && withGrantOption == 0)
                {
                    JOptionPane.showMessageDialog(this, "Bạn không có tùy chọn WITH GRANT OPTION đối với quyền " + privilege);
                }
                else {
                    // Gọi Stored Procedure để thu hồi quyền mà không bao gồm "WITH GRANT OPTION"
                    CallableStatement cstmt = con.prepareCall("{call REVOKE_PRIVILEGE(?, ?, ?)}");
                    cstmt.setString(1, user);
                    cstmt.setString(2, (schema + "." +tableName).toUpperCase());
                    cstmt.setString(3, privilege);
                    cstmt.execute();
                    // Hiển thị thông báo khi thực hiện thành công
                    JOptionPane.showMessageDialog(this, "Thu hồi quyền thành công cho người dùng " + user + "!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            

                // Đóng kết nối
                con.close();

                // Đặt lại các lựa chọn về trạng thái ban đầu
                rd_Select.setSelected(false);
                rd_Insert.setSelected(false);
                rd_Update.setSelected(false);
                rd_Delete.setSelected(false);

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }

// Phương thức để kiểm tra quyền của người dùng và kích hoạt các nút radio tương ứng
private void kiemTraQuyen() {
    String ten_user_can_thao_tac_den_bang = txt_user_can_thao_tac_den_bang.getText();
    String ten_bang = txt_table.getText();
    try {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        Connection con = DriverManager.getConnection(url, this.username, password);

        // Thực hiện câu truy vấn SQL để kiểm tra quyền
        String sql = "SELECT privilege FROM dba_tab_privs WHERE grantee = ? AND table_name = ? AND (privilege = 'SELECT' OR privilege = 'INSERT' OR privilege = 'UPDATE' OR privilege = 'DELETE')";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, ten_user_can_thao_tac_den_bang.toUpperCase()); // Chuyển tên người dùng thành chữ in hoa
        pst.setString(2, ten_bang.toUpperCase()); // Chuyển tên bảng thành chữ in hoa

        ResultSet rs = pst.executeQuery();

        boolean hasSelect = false;
        boolean hasInsert = false;
        boolean hasUpdate = false;
        boolean hasDelete = false;

        while (rs.next()) {
            String privilege = rs.getString("privilege");

            if (privilege.equals("SELECT")) {
                hasSelect = true;
            } else if (privilege.equals("INSERT")) {
                hasInsert = true;
            } else if (privilege.equals("UPDATE")) {
                hasUpdate = true;
            } else if (privilege.equals("DELETE")) {
                hasDelete = true;
            }
        }

        // Enable hoặc vô hiệu hóa các nút radio tương ứng dựa trên quyền
        rd_Select.setEnabled(hasSelect);
        rd_Insert.setEnabled(hasInsert);
        rd_Update.setEnabled(hasUpdate);
        rd_Delete.setEnabled(hasDelete);

        con.close();
    } catch (SQLException e) {
        System.out.println("Lỗi: " + e.getMessage());
        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}


// Phương thức để thu hồi quyền
private void thuHoiQuyen() {
    try {
        rdWithGrantOption.setEnabled(false);
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        Connection con = DriverManager.getConnection(url, this.username, password);

        String ten = txt_ten_user.getText();
        String tableName = txt_table.getText();
        String schema = txt_ten_user.getText(); // Thêm biến schema
        String user = txt_user_can_thao_tac_den_bang.getText();

        boolean hasAnyPrivilege = false; // Biến để kiểm tra xem người dùng có bất kỳ quyền nào không
        boolean hasSelect = false;
        boolean hasInsert = false;
        boolean hasUpdate = false;
        boolean hasDelete = false;


        // Thực hiện câu truy vấn SQL để kiểm tra quyền
        String sql = "SELECT privilege FROM dba_tab_privs WHERE grantee = ? AND table_name = ? AND (privilege = 'SELECT' OR privilege = 'INSERT' OR privilege = 'UPDATE' OR privilege = 'DELETE')";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user.toUpperCase()); // Chuyển tên người dùng thành chữ in hoa
        pst.setString(2, tableName.toUpperCase()); // Chuyển tên bảng thành chữ in hoa

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            hasAnyPrivilege = true; // Đặt cờ là true nếu người dùng có bất kỳ quyền nào
            String privilege = rs.getString("privilege");

            // Đánh dấu các quyền mà người dùng có
            switch (privilege) {
                case "SELECT":
                    hasSelect = true;
                    break;
                case "INSERT":
                    hasInsert = true;
                    break;
                case "UPDATE":
                    hasUpdate = true;
                    break;
                case "DELETE":
                    hasDelete = true;
                    break;
            }
        }


        // Nếu người dùng không có bất kỳ quyền nào, xuất thông báo và không tiếp tục thực hiện
        if (!hasAnyPrivilege) {
            JOptionPane.showMessageDialog(this, "Người dùng không có bất kỳ quyền nào đối với bảng này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Hiển thị thông báo về quyền của người dùng đối với bảng
        StringBuilder privilegeMessage = new StringBuilder("Người dùng " + user + " có các quyền sau đối với bảng " + tableName + ":\n");
        if (hasSelect) {
            privilegeMessage.append("- SELECT\n");
        }
        if (hasInsert) {
            privilegeMessage.append("- INSERT\n");
        }
        if (hasUpdate) {
            privilegeMessage.append("- UPDATE\n");
        }
        if (hasDelete) {
            privilegeMessage.append("- DELETE\n");
        }
        JOptionPane.showMessageDialog(this, privilegeMessage.toString(), "Thông tin quyền", JOptionPane.INFORMATION_MESSAGE);

        // Tiếp tục quá trình thu hồi quyền như thông thường
        CallableStatement cst = con.prepareCall("{call thu_hoi_quyen(?, ?, ?, ?, ?, ?, ?)}");
        cst.setString(1, user);
        cst.setString(2, schema); // Truyền biến schema vào
        cst.setString(3, tableName);
        cst.setBoolean(4, hasSelect);
        cst.setBoolean(5, hasInsert);
        cst.setBoolean(6, hasUpdate);
        cst.setBoolean(7, hasDelete);

        cst.execute();

        // Đóng kết nối
        con.close();

        // Hiển thị thông báo khi thực hiện thành công
        JOptionPane.showMessageDialog(this, "Thu hồi quyền thành công cho người dùng " + user + "!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        // Đặt lại các lựa chọn về trạng thái ban đầu
        rd_Select.setSelected(false);
        rd_Insert.setSelected(false);
        rd_Update.setSelected(false);
        rd_Delete.setSelected(false);
        rdWithGrantOption.setEnabled(true);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}



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
            java.util.logging.Logger.getLogger(Form_GrantObjectPrivilege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_GrantObjectPrivilege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_GrantObjectPrivilege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_GrantObjectPrivilege.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Form_GrantObjectPrivilege form = new Form_GrantObjectPrivilege(); // Tạo một đối tượng của lớp Form_GrantObjectPrivilege
                form.setVisible(true); // Hiển thị form
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnThuHoi1Quyen;
    private javax.swing.JButton Btn_Thu_Hoi;
    private javax.swing.JButton Grant_Delete;
    private javax.swing.JButton Grant_Select;
    private javax.swing.JButton Grant_Update;
    private javax.swing.JButton KiemTraCoQuyenDoChua;
    private javax.swing.JButton btnInsert;
    private javax.swing.JComboBox<String> cpuser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JRadioButton rdWithGrantOption;
    private javax.swing.JRadioButton rd_Delete;
    private javax.swing.JRadioButton rd_Insert;
    private javax.swing.JRadioButton rd_Select;
    private javax.swing.JRadioButton rd_Update;
    private javax.swing.JTable table_name;
    private javax.swing.JTextField txt_table;
    private javax.swing.JTextField txt_ten_user;
    private javax.swing.JTextField txt_user_can_thao_tac_den_bang;
    private javax.swing.JTable user;
    // End of variables declaration//GEN-END:variables
}
