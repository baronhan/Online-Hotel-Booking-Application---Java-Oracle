package hotelbooking;

import hotelbooking.dao.Form_HomeDao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;



public class Form_Home extends javax.swing.JFrame {
    
    public static String username1 = Form_Login.userName;
    public static String password1 = Form_Login.password;
    private Form_HomeDao hotelBookingDAO;
    
    public Form_Home(String username, String password) {
        initComponents();
        Form_Home.username1 = username;
        Form_Home.password1 = password; 
        hotelBookingDAO = new Form_HomeDao();
        CheckSessionWorker sessionWorker = new CheckSessionWorker(this);
        sessionWorker.execute();

        loadLastLogin();
    }
    private void loadLastLogin() {
        try {
            String lastLoginValue = hotelBookingDAO.getLastLogin(username1);
            txtWelcome.setText("Welcome " + username1.toUpperCase());
            txtLastLogin.setText("Last Login: " + lastLoginValue);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Form_Home() {
        initComponents();
        hotelBookingDAO = new Form_HomeDao();
        CheckSessionWorker sessionWorker = new CheckSessionWorker(this);
        sessionWorker.execute();

        loadLastLogin();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnUser = new javax.swing.JButton();
        btnRooms = new javax.swing.JButton();
        btnHistoryBooking = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtWelcome = new javax.swing.JLabel();
        txtLastLogin = new javax.swing.JLabel();
        btnContact = new javax.swing.JButton();
        hotelTitle = new javax.swing.JLabel();
        imgHotel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(195, 319));

        btnUser.setBackground(new java.awt.Color(0, 153, 153));
        btnUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUser.setForeground(new java.awt.Color(255, 255, 255));
        btnUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/2.png"))); // NOI18N
        btnUser.setText(" Cá nhân");
        btnUser.setBorderPainted(false);
        btnUser.setDefaultCapable(false);
        btnUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserActionPerformed(evt);
            }
        });

        btnRooms.setBackground(new java.awt.Color(0, 153, 153));
        btnRooms.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRooms.setForeground(new java.awt.Color(255, 255, 255));
        btnRooms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/6.png"))); // NOI18N
        btnRooms.setText(" Danh sách phòng");
        btnRooms.setBorderPainted(false);
        btnRooms.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRooms.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomsActionPerformed(evt);
            }
        });

        btnHistoryBooking.setBackground(new java.awt.Color(0, 153, 153));
        btnHistoryBooking.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHistoryBooking.setForeground(new java.awt.Color(255, 255, 255));
        btnHistoryBooking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/3.png"))); // NOI18N
        btnHistoryBooking.setText(" Lịch sử đặt");
        btnHistoryBooking.setBorderPainted(false);
        btnHistoryBooking.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHistoryBooking.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHistoryBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryBookingActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(0, 153, 153));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Đăng xuất");
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        txtWelcome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtWelcome.setForeground(new java.awt.Color(255, 255, 255));
        txtWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtWelcome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtLastLogin.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtLastLogin.setForeground(new java.awt.Color(255, 255, 255));
        txtLastLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLastLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnContact.setBackground(new java.awt.Color(0, 153, 153));
        btnContact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnContact.setForeground(new java.awt.Color(255, 255, 255));
        btnContact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/2.png"))); // NOI18N
        btnContact.setText("Hỗ trợ chat");
        btnContact.setBorderPainted(false);
        btnContact.setDefaultCapable(false);
        btnContact.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnContact.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
            .addComponent(btnHistoryBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLastLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(btnContact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(txtWelcome)
                .addGap(18, 18, 18)
                .addComponent(txtLastLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btnUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRooms)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHistoryBooking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnContact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit))
        );

        hotelTitle.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        hotelTitle.setForeground(new java.awt.Color(0, 102, 102));
        hotelTitle.setText("hotelbooking");

        imgHotel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgHotel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/hotel.jpg"))); // NOI18N
        imgHotel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imgHotel.setFocusTraversalPolicyProvider(true);
        imgHotel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(hotelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(imgHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(hotelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private static class CheckSessionWorker extends SwingWorker<Boolean, Void> {
        private final Form_Home formHome;

        public CheckSessionWorker(Form_Home formHome) {
            this.formHome = formHome;
        }

       @Override
        protected Boolean doInBackground() throws Exception {
            boolean sessionExists = true;
            Form_HomeDao dao = new Form_HomeDao();
            while (sessionExists) {
                sessionExists = dao.checkSession(formHome.username1);
                Thread.sleep(1000);
            }

            return sessionExists;
        }


        @Override
        protected void done() {
            try {
                boolean sessionValid = get(); // Lấy kết quả từ doInBackground()
                if (!sessionValid) {
                    // Nếu session không tồn tại, đóng form và thoát ứng dụng
                    formHome.dispose();
                    System.exit(0);
                } else {
                    //System.out.println("hotelbooking.Form_Home.CheckSessionWorker.done()");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        try {
            hotelBookingDAO.killUserSessions(username1);
            Thread.sleep(3000);
        } catch (SQLException | ClassNotFoundException | InterruptedException e) {
            JOptionPane.showMessageDialog(this, "Failed to close connection. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnHistoryBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryBookingActionPerformed
        // TODO add your handling code here:
        Form_HistoryBooking jf2 = new Form_HistoryBooking();
        jf2.show();
    }//GEN-LAST:event_btnHistoryBookingActionPerformed

    private void btnRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomsActionPerformed
        try {
            int result = hotelBookingDAO.checkInsertPrivilege(username1);
            if (result == 1) {
                Form_Rooms form = new Form_Rooms();
                form.show();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Người dùng không có quyền INSERT vào bảng HOTELBOOKING.BOOKINGS");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRoomsActionPerformed

    private void btnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserActionPerformed
        try {
            int selectResult = hotelBookingDAO.checkSelectPrivilege(username1);
            int updateResult = hotelBookingDAO.checkUpdatePrivilege(username1);

            if (selectResult == 1 && updateResult == 1) {
                Form_User formUser = new Form_User();
                formUser.show();
            } else if (selectResult == 1) {
                Form_User formUser = new Form_User();
                formUser.show();
            } else if (updateResult == 1) {
                JOptionPane.showMessageDialog(null, "Người dùng không có quyền SELECT.");
            } else {
                JOptionPane.showMessageDialog(null, "Người dùng không có quyền SELECT và UPDATE.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUserActionPerformed

    private void btnContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactActionPerformed
        Form_ClientChatter form = new Form_ClientChatter();
        form.show();
    }//GEN-LAST:event_btnContactActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(Form_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContact;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHistoryBooking;
    private javax.swing.JButton btnRooms;
    private javax.swing.JButton btnUser;
    private javax.swing.JLabel hotelTitle;
    private javax.swing.JLabel imgHotel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtLastLogin;
    private javax.swing.JLabel txtWelcome;
    // End of variables declaration//GEN-END:variables
}
