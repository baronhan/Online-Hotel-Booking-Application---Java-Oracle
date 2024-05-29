package hotelbooking;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Form_Booking extends javax.swing.JFrame {
    
    private String username = hotelbooking.Form_Login.userName;
    private String password = hotelbooking.Form_Login.password;
    private String serviceId = hotelbooking.Form_Services.serviceId_;
    private String roomId = hotelbooking.Form_Rooms.roomId_;
    private String RoomName;
    private float RoomPrice;
    private String RoomPicture;
    public static String UserId;
    private String OrderBy;
    private String PhoneNumber;
    private String EmailAddress;
    private String Address;
    private String CardCreditNumber;
    private String ServiceName;
    private float ServicePrice;
    private float Total;
    
    
    public Form_Booking() {
        initComponents();
        initPaymentDetails();
        checkValuesOfDate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelRoomName = new javax.swing.JLabel();
        labelRoomPrice = new javax.swing.JLabel();
        labelPicture = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtOrderBy = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        DateCheckIn = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        DateCheckOut = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEmailAddress = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCardCreditNumber = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtServiceTotal = new javax.swing.JTextField();
        txtServiceName = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnPay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PAYMENT DETAILS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(258, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(311, 311, 311))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        labelRoomName.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N

        labelRoomPrice.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N

        jLabel5.setText("Order By:");

        txtOrderBy.setEditable(false);

        jLabel6.setText("Phone Number:");

        txtPhoneNumber.setEditable(false);

        jLabel7.setText("Address:");

        txtAddress.setEditable(false);

        jLabel8.setText("Check In:");

        DateCheckIn.setDateFormatString("dd-MM-yyyy");

        jLabel9.setText("Check Out:");

        DateCheckOut.setDateFormatString("dd-MM-yyyy");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("PAYMENT DETAILS");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel11.setText("Complete your purchase by providing your payment details");

        jLabel12.setText("Email Address:");

        txtEmailAddress.setEditable(false);

        jLabel13.setText("Card Credit Number:");

        jLabel14.setText("Subtotal:");

        jLabel15.setText("Service Total:");

        jLabel16.setText("Service Name:");

        jLabel17.setText("Total:");

        txtSubtotal.setEditable(false);

        txtServiceTotal.setEditable(false);

        txtServiceName.setEditable(false);

        txtTotal.setEditable(false);

        btnPay.setBackground(new java.awt.Color(0, 102, 102));
        btnPay.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        btnPay.setForeground(new java.awt.Color(255, 255, 255));
        btnPay.setText("PAY");
        btnPay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DateCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAddress)
                                        .addComponent(DateCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addComponent(txtOrderBy, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPhoneNumber))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelRoomPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(labelPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(106, 106, 106)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailAddress)
                    .addComponent(txtCardCreditNumber)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubtotal)
                            .addComponent(txtServiceTotal)
                            .addComponent(txtServiceName)
                            .addComponent(txtTotal)))
                    .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRoomName)
                    .addComponent(labelRoomPrice)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtCardCreditNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtServiceTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(labelPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOrderBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtServiceName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPay))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(DateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(DateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 43, Short.MAX_VALUE))
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
    private void checkValuesOfDate()
    {
        DateCheckIn.getDateEditor().addPropertyChangeListener(e -> {
            java.util.Date dateCheckIn = DateCheckIn.getDate();
            java.util.Date dateCheckOut = DateCheckOut.getDate();
            if(dateCheckIn != null && dateCheckOut != null && dateCheckIn.after(dateCheckOut))
            {
                // Nếu ngày check-in lớn hơn ngày check-out, đặt ngày check-out thành ngày check-in
                DateCheckOut.setDate(dateCheckIn);
                JOptionPane.showMessageDialog(this, "Ngày CheckIn lớn hơn Ngày CheckOut! Vui lòng chọn lại!");
            }
            
        });
        
        DateCheckOut.getDateEditor().addPropertyChangeListener(e -> {
            java.util.Date dateCheckIn = DateCheckIn.getDate();
            java.util.Date dateCheckOut = DateCheckOut.getDate();
            if(dateCheckIn != null && dateCheckOut != null && dateCheckOut.before(dateCheckIn))
            {
                // Nếu ngày check-in lớn hơn ngày check-out, đặt ngày check-out thành ngày check-in
                DateCheckIn.setDate(dateCheckOut);
                JOptionPane.showMessageDialog(this, "Ngày CheckOut nhỏ hơn Ngày CheckIn! Vui lòng chọn lại!");
            }
        });
    }
    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        java.util.Date dateCheckIn = DateCheckIn.getDate();
        java.util.Date dateCheckOut = DateCheckOut.getDate();
        CardCreditNumber = txtCardCreditNumber.getText();
        // Chuyển đổi java.util.Date thành java.sql.Date
        java.sql.Date sqlDateCheckIn = new java.sql.Date(dateCheckIn.getTime());
        java.sql.Date sqlDateCheckOut = new java.sql.Date(dateCheckOut.getTime());

        //Định dạng ngày sang dạng chuỗi
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDateCheckIn = dateFormat.format(dateCheckIn);
        String strDateCheckOut = dateFormat.format(dateCheckOut);
        System.out.println(sqlDateCheckIn + " + " + sqlDateCheckOut );
        
        if(!CardCreditNumber.isEmpty())
        {
            try{
                //Kết nối tới cơ sở dữ liệu
                Connection conn = connectToOracle();
                             
                // Chuẩn bị câu lệnh thực thi
                CallableStatement stmt_checkdate = conn.prepareCall("{ ? = call SYS.CheckDates(?, ?) }");


                stmt_checkdate.setDate(2, sqlDateCheckIn);
                stmt_checkdate.setDate(3, sqlDateCheckOut);

                // Đăng ký tham số đầu ra và thực thi câu lệnh
                stmt_checkdate.registerOutParameter(1, Types.BOOLEAN);
                stmt_checkdate.execute();

                // Lấy kết quả từ tham số đầu ra
                boolean result = stmt_checkdate.getBoolean(1);
                if (result == false) {
                    JOptionPane.showMessageDialog(this, "Ngày check-in hoặc check-out không hợp lệ.");
                    JOptionPane.showMessageDialog(this, "Payment Failure");
                }
                else
                {
                    //Chuẩn bị câu lệnh stored procedure AddBooking
                    CallableStatement stmt_addbooking = conn.prepareCall("{call SYS.AddBooking(?, ?, ?, ?, ? , ?, ?)}");
                    stmt_addbooking.setString(1, UserId);
                    stmt_addbooking.setString(2, roomId);
                    stmt_addbooking.setString(3, serviceId);
                    stmt_addbooking.setDate(4, sqlDateCheckIn);
                    stmt_addbooking.setDate(5, sqlDateCheckOut);
                    stmt_addbooking.setString(6, CardCreditNumber);
                    stmt_addbooking.setFloat(7, Total);

                    //Thực thi 
                    stmt_addbooking.execute();
                    JOptionPane.showMessageDialog(this, "Payment Success");
                
                    Form_HistoryBooking form = new Form_HistoryBooking();
                    form.show();
                    this.dispose();
                    
                    // Đóng kết nối
                    conn.close();
                }
            }catch(SQLException e)
            {
                 e.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter your credit card number");
        }
    }//GEN-LAST:event_btnPayActionPerformed
    private void initPaymentDetails(){
        try {
            // Kết nối tới cơ sở dữ liệu
            Connection conn = connectToOracle();
           
            // Chuẩn bị cuộc gọi stored procedure GETROOMINFO
            CallableStatement stmt_getroominfo = conn.prepareCall("{call SYS.GetRoomInfo(?, ?, ?, ?)}");
            stmt_getroominfo.setString(1, roomId); // Tham số đầu vào
            stmt_getroominfo.registerOutParameter(2, Types.VARCHAR); // Tham số đầu ra: roomName
            stmt_getroominfo.registerOutParameter(3, Types.FLOAT); // Tham số đầu ra: price
            stmt_getroominfo.registerOutParameter(4, Types.VARCHAR); // Tham số đầu ra: roomPicture

            // Thực thi stored procedure
            stmt_getroominfo.execute();

            // Lấy kết quả
            RoomName = stmt_getroominfo.getString(2);
            RoomPrice = stmt_getroominfo.getFloat(3);
            RoomPicture = stmt_getroominfo.getString(4);
            
            // Chuẩn bị cuộc gọi stored procedure GETUSERINFO
            CallableStatement stmt_getuserinfo = conn.prepareCall("{call SYS.GetUserInfo(?, ?, ?, ?, ?, ?)}");
            stmt_getuserinfo.setString(1, username);
            stmt_getuserinfo.registerOutParameter(2, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(3, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(4, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(5, Types.VARCHAR);
            stmt_getuserinfo.registerOutParameter(6, Types.VARCHAR);
            
            // Thực thi stored procedure
            stmt_getuserinfo.execute();
            
            //Lấy kết quả
            OrderBy = stmt_getuserinfo.getString(2);
            EmailAddress = stmt_getuserinfo.getString(3);
            Address = stmt_getuserinfo.getString(4);
            PhoneNumber = stmt_getuserinfo.getString(5);
            UserId = stmt_getuserinfo.getString(6);
            
            // Chuẩn bị cuộc gọi stored procedure GETSERVICEINFO
            CallableStatement stmt_getserviceinfo = conn.prepareCall("{call SYS.GetServiceInfo(?, ?, ?)}");
            stmt_getserviceinfo.setString(1, serviceId);
            stmt_getserviceinfo.registerOutParameter(2, Types.VARCHAR);
            stmt_getserviceinfo.registerOutParameter(3, Types.FLOAT);
            
            //Thực thi stored procedure
            stmt_getserviceinfo.execute();
            
            //Lấy kết quả
            ServiceName = stmt_getserviceinfo.getString(2);
            ServicePrice = stmt_getserviceinfo.getFloat(3);
            Total = ServicePrice + RoomPrice;
            
            // In kết quả ra màn hình
            labelRoomName.setText(RoomName);
            labelRoomPrice.setText(Float.toString(RoomPrice));
            txtAddress.setText(Address);
            txtEmailAddress.setText(EmailAddress);
            txtOrderBy.setText(OrderBy);
            txtPhoneNumber.setText(PhoneNumber);
            txtSubtotal.setText(Float.toString(RoomPrice));
            txtTotal.setText(Float.toString(Total));
            txtServiceName.setText(ServiceName);
            txtServiceTotal.setText(Float.toString(ServicePrice));
            String imagePath = RoomPicture;
                    try {
                        //BufferedImage image = ImageIO.read(imagePath);
                        BufferedImage image = ImageIO.read(getClass().getResource(imagePath));
                        ImageIcon icon = new ImageIcon(image);
                        labelPicture.setIcon(icon); // Gán hình ảnh cho JLabel
                    } catch (IOException ex) {
                        Logger.getLogger(Form_Rooms.class.getName()).log(Level.SEVERE, null, ex);
                        // Hiển thị thông báo lỗi nếu việc đọc hình ảnh không thành công
                        JOptionPane.showMessageDialog(this, "Không thể đọc hình ảnh.");
                    }
            //Lấy ngày hiện tại của hệ thống
//            Date currentDate = new Date();
//            //Định dạng ngày tháng
//            DateCheckIn.setDate(currentDate);
//            DateCheckOut.setDate(currentDate);
//            DateCheckIn.setDateFormatString("yyyy-MM-dd");
//            DateCheckOut.setDateFormatString("yyyy-MM-dd");
            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection connectToOracle() {
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
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Booking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateCheckIn;
    private com.toedter.calendar.JDateChooser DateCheckOut;
    private javax.swing.JButton btnPay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelPicture;
    private javax.swing.JLabel labelRoomName;
    private javax.swing.JLabel labelRoomPrice;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCardCreditNumber;
    private javax.swing.JTextField txtEmailAddress;
    private javax.swing.JTextField txtOrderBy;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtServiceName;
    private javax.swing.JTextField txtServiceTotal;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
