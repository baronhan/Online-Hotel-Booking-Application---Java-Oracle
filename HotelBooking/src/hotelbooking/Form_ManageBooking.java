/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelbooking;

import hotelbooking.model.Booking;
import hotelbooking.model.Service;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import java.sql.CallableStatement;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Types;

public class Form_ManageBooking extends javax.swing.JFrame {
    private String username = hotelbooking.Form_Login.userName;
    private String password = hotelbooking.Form_Login.password;
    private String booking_ID;
    private String room_ID;
    private double totalPrice = 0;
    private Map<JComboBox<String>, List<String>> originalValuesMap;
    private List<Booking> list = new ArrayList<>();
    private DefaultTableModel tbModel;
    private String roomId;
    private String serviceId;
    
    public Form_ManageBooking() {
        initComponents();
        initTable();
        initBooking();
        fillComboBox();
        checkValuesOfDate();
        originalValuesMap = new HashMap<>();
        originalValuesMap.put(cbUserID, getComboBoxValues(cbUserID));
        originalValuesMap.put(cbRoomId, getComboBoxValues(cbRoomId));
        originalValuesMap.put(cbServiceId, getComboBoxValues(cbServiceId));
        // Lắng nghe sự kiện khi người dùng thay đổi lựa chọn trong combobox
        cbServiceId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Gọi phương thức tính tổng giá trị total với giá trị mới từ combobox
                    updateTotalPrice();
                }
            }
        });

        cbRoomId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Gọi phương thức tính tổng giá trị total với giá trị mới từ combobox
                    updateTotalPrice();
                }
            }
        });

    }
    // Phương thức để tính tổng giá trị total dựa trên roomId và serviceId
    private double calculateTotalPrice(String roomId, String serviceId) {
        double totalPrice = 0;

        // Kết nối tới cơ sở dữ liệu
        try (Connection conn = connectToOracle(username, password)) {
            // Gọi stored procedure để tính tổng giá trị total
            try (CallableStatement cst = conn.prepareCall("{ ? = call calculateTotalPrice(?, ?) }")) {
                // Đăng ký tham số đầu ra của stored procedure
                cst.registerOutParameter(1, Types.DOUBLE);
                // Gán giá trị cho các tham số đầu vào của stored procedure
                cst.setString(2, roomId);
                cst.setString(3, serviceId);
                // Thực thi stored procedure
                cst.execute();
                // Lấy giá trị tổng giá trị total trả về từ stored procedure
                totalPrice = cst.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPrice;
    }
    // Phương thức để tính tổng giá trị total
    private void updateTotalPrice() {
        // Lấy giá trị mới từ combobox
        String roomId_ = (String) cbRoomId.getSelectedItem();
        String serviceId_ = (String) cbServiceId.getSelectedItem();

        // Gọi hàm tính tổng giá trị total và cập nhật giao diện người dùng
        double totalPrice = calculateTotalPrice(roomId_, serviceId_);
        txtTotal.setText(String.valueOf(totalPrice));
    }

    private void checkValuesOfDate()
    {
        dateCheckIn.getDateEditor().addPropertyChangeListener(e -> {
            java.util.Date dateCheckIn_ = dateCheckIn.getDate();
            java.util.Date dateCheckOut_ = dateCheckOut.getDate();
            if(dateCheckIn_ != null && dateCheckOut_ != null && dateCheckIn_.after(dateCheckOut_))
            {
                // Nếu ngày check-in lớn hơn ngày check-out, đặt ngày check-out thành ngày check-in
                dateCheckOut.setDate(dateCheckIn_);
                JOptionPane.showMessageDialog(this, "Ngày CheckIn lớn hơn Ngày CheckOut! Vui lòng chọn lại!");
            }
            
        });
        
        dateCheckOut.getDateEditor().addPropertyChangeListener(e -> {
            java.util.Date dateCheckIn_ = dateCheckIn.getDate();
            java.util.Date dateCheckOut_ = dateCheckOut.getDate();
            if(dateCheckIn_ != null && dateCheckOut_ != null && dateCheckOut_.before(dateCheckIn_))
            {
                // Nếu ngày check-in lớn hơn ngày check-out, đặt ngày check-out thành ngày check-in
                dateCheckIn.setDate(dateCheckOut_);
                JOptionPane.showMessageDialog(this, "Ngày CheckOut nhỏ hơn Ngày CheckIn! Vui lòng chọn lại!");
            }
        });
    }
    private void fillComboBox() {
        Connection connection = connectToOracle(username, password);
        if (connection != null) {
            CallableStatement cstmt = null;
            ResultSet rs = null;
            try {
                // Gọi stored procedure từ cơ sở dữ liệu để lấy danh sách roomId
                cstmt = connection.prepareCall("{call get_room_ids(?)}");
                cstmt.registerOutParameter(1, OracleTypes.CURSOR); // Đăng ký tham số đầu ra
                cstmt.execute();
                rs = (ResultSet) cstmt.getObject(1);

                // Xóa tất cả các mục cũ trong combobox cbRoomId
                cbRoomId.removeAllItems();

                // Đổ dữ liệu từ ResultSet vào combobox cbRoomId
                while (rs.next()) {
                    cbRoomId.addItem(rs.getString("roomId"));
                }

                // Gọi stored procedure từ cơ sở dữ liệu để lấy danh sách userId
                cstmt = connection.prepareCall("{call get_user_ids(?)}");
                cstmt.registerOutParameter(1, OracleTypes.CURSOR); // Đăng ký tham số đầu ra
                cstmt.execute();
                rs = (ResultSet) cstmt.getObject(1);

                // Xóa tất cả các mục cũ trong combobox cbUserId
                cbUserID.removeAllItems();

                // Đổ dữ liệu từ ResultSet vào combobox cbUserId
                while (rs.next()) {
                    cbUserID.addItem(rs.getString("userId"));
                }
                // Gọi stored procedure từ cơ sở dữ liệu để lấy danh sách serviceId
                cstmt = connection.prepareCall("{call get_service_ids(?)}");
                cstmt.registerOutParameter(1, OracleTypes.CURSOR); // Đăng ký tham số đầu ra
                cstmt.execute();
                rs = (ResultSet) cstmt.getObject(1);

                // Xóa tất cả các mục cũ trong combobox cbServiceId
                cbServiceId.removeAllItems();

                // Đổ dữ liệu từ ResultSet vào combobox cbServiceId
                while (rs.next()) {
                    cbServiceId.addItem(rs.getString("serviceId"));
                }
                //Lấy ngày hiện tại của hệ thống
                Date currentDate = new Date();
                //Định dạng ngày tháng
                dateCheckIn.setDate(currentDate);
                dateCheckOut.setDate(currentDate);
                dateCheckIn.setDateFormatString("dd-MM-yyyy");
                dateCheckOut.setDateFormatString("dd-MM-yyyy");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi truy vấn. Error: " + ex.getMessage());
            } finally {
                // Đóng kết nối và các tài nguyên khác
                try {
                    if (rs != null) rs.close();
                    if (cstmt != null) cstmt.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi đóng kết nối. Error: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Kết nối đến cơ sở dữ liệu không thành công!");
        }
    }


    private void fillTable() {
        while (tbModel.getRowCount() > 0)
            tbModel.removeRow(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        txtTotal.setText(Double.toString(totalPrice));
        for (Booking booking : list) {
            // Chuyển đổi các kiểu dữ liệu không phải là String thành String
            String bookingID = booking.getBookingId();
            String userId = booking.getUserId();
            String roomId = booking.getRoomId();
            String serviceId = booking.getServiceId();
            String checkIn = dateFormat.format(booking.getCheckIn());
            String checkOut = dateFormat.format(booking.getCheckOut());
            String creditNumber = booking.getCreditNumber();
            String total = Float.toString(booking.getTotal());
            // Thêm hàng mới vào DefaultTableModel
            tbModel.addRow(new String[]{bookingID, userId, roomId, serviceId, checkIn, checkOut, creditNumber, total});
            }

            // Thông báo cho table model rằng dữ liệu đã thay đổi
            tbModel.fireTableDataChanged();
    }
    private void initTable()
    {
        tbModel = new DefaultTableModel();
        String[] columns = new String[]{"BookingID", "UserID", "RoomID", "ServiceID", "CheckIn", "CheckOut", "CreditNumber", "Total"};
        tbModel.setColumnIdentifiers(columns);
        
        tblBooking.setModel(tbModel);
    }
    private void initBooking()
    {
        try{
            // Connect to the database
            Connection con = connectToOracle(username, password);
            String sql = "SELECT * FROM hotelbooking.bookings";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //Process the result set and populate the list
            while(rs.next()){
                String bookingId = rs.getString("bookingId");
                String userId = rs.getString("userId");
                String roomId = rs.getString("roomId");
                String serviceId = rs.getString("serviceId");
                Date checkIn = rs.getDate("checkIn");
                Date checkOut = rs.getDate("checkOut");
                String creditnumber = rs.getString("creditNumber");
                float total = rs.getFloat("total");
                Booking booking = new Booking(bookingId, userId, roomId, serviceId, checkIn, checkOut, creditnumber, total);
                list.add(booking);
            }
            // Close resources
            rs.close();
            pst.close();
            con.close();   
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateCheckIn = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        dateCheckOut = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtCreditNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        cbUserID = new javax.swing.JComboBox<>();
        cbRoomId = new javax.swing.JComboBox<>();
        cbServiceId = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBooking = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));

        jLabel5.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BOOKING MANAGEMENT");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("UserID:");

        jLabel2.setText("RoomID:");

        jLabel3.setText("ServiceID:");

        jLabel4.setText("Check In:");

        jLabel6.setText("Check Out:");

        jLabel7.setText("CreditNumber:");

        jLabel8.setText("Total:");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Function"));

        btnAdd.setBackground(new java.awt.Color(0, 102, 102));
        btnAdd.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setBackground(new java.awt.Color(0, 102, 102));
        btnRemove.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        btnRemove.setForeground(new java.awt.Color(255, 255, 255));
        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 102, 102));
        btnUpdate.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 102, 102));
        btnReset.setFont(new java.awt.Font("Showcard Gothic", 0, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnAdd)
                .addGap(65, 65, 65)
                .addComponent(btnRemove)
                .addGap(56, 56, 56)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addGap(56, 56, 56))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnRemove)
                    .addComponent(btnUpdate)
                    .addComponent(btnReset))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        cbUserID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbRoomId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbServiceId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateCheckIn, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                            .addComponent(dateCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCreditNumber)
                            .addComponent(txtTotal)
                            .addComponent(cbUserID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbRoomId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbServiceId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbRoomId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbServiceId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCreditNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Booking", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblBooking.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBookingMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBooking);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("List of Bookings", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        StringBuilder sb = new StringBuilder();
        Connection connection = connectToOracle(username, password);
        String userId = (String) cbUserID.getSelectedItem();
        roomId = (String) cbRoomId.getSelectedItem();
        serviceId = (String) cbServiceId.getSelectedItem();
        String creditNumber = txtCreditNumber.getText();
        float total = Float.parseFloat(txtTotal.getText());

        java.util.Date dateCheckIn_ = dateCheckIn.getDate();
        java.util.Date dateCheckOut_ = dateCheckOut.getDate();

        // Chuyển đổi java.util.Date sang java.sql.Date
        java.sql.Date sqlDateCheckIn = new java.sql.Date(dateCheckIn_.getTime());
        java.sql.Date sqlDateCheckOut = new java.sql.Date(dateCheckOut_.getTime());
        
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(connection != null)
        {
            try (CallableStatement cs = connection.prepareCall("{call getRoomStatusByRoomId(?, ?)}")) {
                cs.setString(1, roomId);
                cs.registerOutParameter(2, java.sql.Types.INTEGER);
                cs.execute();
                
                // Lấy giá trị trả về
                int roomStatus = cs.getInt(2);
                
                if(roomStatus == 1)
                {
                    try {         
                        // Gọi stored procedure insert_room từ Java
                        CallableStatement cst = connection.prepareCall("{call addBooking(?,?,?,?,?,?,?)}");
                        cst.setString(1, userId);
                        cst.setString(2, roomId);
                        cst.setString(3, serviceId);
                        cst.setDate(4, sqlDateCheckIn);
                        cst.setDate(5, sqlDateCheckOut);
                        cst.setString(6, creditNumber);                      
                        cst.setFloat(7, total);
                        cst.execute();
                        cst.close();

                        JOptionPane.showMessageDialog(this, "Thêm mới Booking thành công");
                        initBooking();
                        //fillTable();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(this, "Thêm mới Booking không thành công");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Mã phòng này không còn trống! Vui lòng chọn Mã phòng khác!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        StringBuilder sb = new StringBuilder();
        Connection connection = connectToOracle(username, password);
        if(booking_ID.equals(""))
        {
            sb.append("BookingID is empty\n");
        }
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(connection != null)
        {
            try{
                for(Booking booking : list)
                {
                    if(booking.getBookingId().equals(booking_ID))
                    {
                        int choice = JOptionPane.showConfirmDialog(this, "Do you want to delete", "Confirm", JOptionPane.YES_NO_OPTION);
                        if(choice == JOptionPane.YES_OPTION)
                        {
                            //Chuẩn bị câu lệnh sp
                            String deletebooking = "{call deleteBookingByBookingId(?, ?)}";
                            //Chuẩn bị và thi hành câu lệnh sp
                            CallableStatement deleteStatement = connection.prepareCall(deletebooking);
                            deleteStatement.setString(1, booking_ID);
                            deleteStatement.setString(2, room_ID);
                            deleteStatement.execute();
                            deleteStatement.close();

                            list.remove(booking);
                            fillTable();
                            JOptionPane.showMessageDialog(this, "Booking đã được xóa thành công");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Không tìm thấy Booking!");
            }catch(SQLException e)
            {
                JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        StringBuilder sb = new StringBuilder();
        Connection connection = connectToOracle(username, password);
        String userId = (String) cbUserID.getSelectedItem();
        roomId = (String) cbRoomId.getSelectedItem();
        serviceId = (String) cbServiceId.getSelectedItem();
        String creditNumber = txtCreditNumber.getText();
        float total = Float.parseFloat(txtTotal.getText());

        java.util.Date dateCheckIn_ = dateCheckIn.getDate();
        java.util.Date dateCheckOut_ = dateCheckOut.getDate();

        // Chuyển đổi java.util.Date sang java.sql.Date
        java.sql.Date sqlDateCheckIn = new java.sql.Date(dateCheckIn_.getTime());
        java.sql.Date sqlDateCheckOut = new java.sql.Date(dateCheckOut_.getTime());
        
        if(booking_ID.equals(""))
        {
            sb.append("Booking is empty\n");
        }
        if(sb.length() > 0)
        {
            JOptionPane.showMessageDialog(this, sb.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(connection != null)
        {
            try {
                for(Booking booking : list)
                {
                    if(booking.getBookingId().equals(booking_ID))
                    {
                        try (CallableStatement cs = connection.prepareCall("{call getRoomStatusByRoomId(?, ?)}")) {
                            cs.setString(1, roomId);
                            cs.registerOutParameter(2, java.sql.Types.INTEGER);
                            cs.execute();

                            // Lấy giá trị trả về
                            int roomStatus = cs.getInt(2);

                            if(roomStatus == 1 || (roomStatus == 0 && room_ID.equals(cbRoomId.getSelectedItem())))
                            {
                                // Gọi stored procedure
                                CallableStatement cst = connection.prepareCall("{call updateBookingByBookingId(?,?,?,?,?,?,?,?,?)}");
                                cst.setString(1, booking_ID);
                                cst.setString(2, userId);
                                cst.setString(3, roomId);
                                cst.setString(4, serviceId);
                                cst.setDate(5, sqlDateCheckIn);
                                cst.setDate(6, sqlDateCheckOut);
                                cst.setString(7, creditNumber);                      
                                cst.setFloat(8, total);
                                cst.setString(9, room_ID);
                                cst.execute();
                                cst.close();
                                fillTable();
                                initBooking();
                                
                                JOptionPane.showMessageDialog(this, "Booking đã được cập nhật");
                                return;
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(this, "Mã phòng này không còn trống! Vui lòng chọn Mã phòng khác!");
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Lỗi khi thực thi stored procedure. Error: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(this, "Cập nhật Booking không thành công");
            }        
        }
    }//GEN-LAST:event_btnUpdateActionPerformed


    private List<String> getComboBoxValues(JComboBox<String> comboBox) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            values.add((String) comboBox.getItemAt(i));
        }
        return values;
    }

    private void resetComboBoxValues() {
        for (JComboBox<String> comboBox : originalValuesMap.keySet()) {
            comboBox.removeAllItems();
            List<String> originalValues = originalValuesMap.get(comboBox);
            for (String value : originalValues) {
                comboBox.addItem(value);
            }
        }
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtCreditNumber.setText("");
        txtTotal.setText("");
        resetComboBoxValues();
        dateCheckIn.setDate(null);
        dateCheckOut.setDate(null);
    }//GEN-LAST:event_btnResetActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        fillTable();
    }//GEN-LAST:event_formWindowOpened

    private void tblBookingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookingMouseClicked
        int selected = tblBooking.getSelectedRow();
        if (selected >= 0) {
            String bookingID_click = (String) tbModel.getValueAt(selected, 0);
            for (Booking booking : list) {
                if (booking.getBookingId().equals(bookingID_click)) {
                    txtCreditNumber.setText(booking.getCreditNumber());
                    txtTotal.setText(Float.toString(booking.getTotal()));
                    
                    booking_ID = booking.getBookingId();
                    room_ID = booking.getRoomId();
                    // Kiểm tra và thiết lập giá trị cho cbUserID
                    String userId = booking.getUserId();
                    setComboBoxValue(cbUserID, userId);

                    // Kiểm tra và thiết lập giá trị cho cbRoomId
                    String roomId = booking.getRoomId();
                    setComboBoxValue(cbRoomId, roomId);

                    // Kiểm tra và thiết lập giá trị cho cbServiceId
                    String serviceId = booking.getServiceId();
                    setComboBoxValue(cbServiceId, serviceId);
                    
                    //Thiết lập giá trị cho Date
                    dateCheckIn.setDate(booking.getCheckIn());
                    dateCheckOut.setDate(booking.getCheckOut());
                    dateCheckIn.setDateFormatString("dd-MM-yyyy");
                    dateCheckOut.setDateFormatString("dd-MM-yyyy");
                    break; // Thoát khỏi vòng lặp sau khi tìm thấy booking
                }
            }
        }
    }//GEN-LAST:event_tblBookingMouseClicked
    private void setComboBoxValue(JComboBox<String> comboBox, String value) {
        boolean found = false;
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).equals(value)) {
                found = true;
                break;
            }
        }
        if (found) {
            comboBox.setSelectedItem(value);
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
            java.util.logging.Logger.getLogger(Form_ManageBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_ManageBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_ManageBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_ManageBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_ManageBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbRoomId;
    private javax.swing.JComboBox<String> cbServiceId;
    private javax.swing.JComboBox<String> cbUserID;
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private com.toedter.calendar.JDateChooser dateCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblBooking;
    private javax.swing.JTextField txtCreditNumber;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
