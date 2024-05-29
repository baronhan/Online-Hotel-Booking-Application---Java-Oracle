/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sound.midi.Sequence;

/**
 *
 * @author LENOVO
 */
public class Class_QRCodeImageReader extends JFrame{
    private JButton btnSelectImage;
    
    public Class_QRCodeImageReader() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("QR Code Image Reader");
        setSize(400, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        btnSelectImage = new JButton("Chọn hình ảnh QR");
        btnSelectImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });

        panel.add(btnSelectImage);
        add(panel);
    }

    void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn hình ảnh QR");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh PNG", "png");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            readQRCode(selectedFile);
        }
    }

    private void readQRCode(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = new MultiFormatReader().decode(bitmap);
            String qrContent = result.getText();
            System.out.println("Nội dung mã QR: " + qrContent);
            
            // Chia chuỗi và lấy tên người dùng và mật khẩu
            String[] parts = qrContent.split("@");
            if (parts.length >= 2) {
                String username = parts[0]; // Tên người dùng
                String password = parts[1]; // Mật khẩu
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                
                // Kết nối đến cơ sở dữ liệu Oracle
                Connection connection = connectToOracle("orcl", username, password);
                if (connection != null) {
                    System.out.println("Kết nối thành công đến cơ sở dữ liệu Oracle.");
                    // Thực hiện các thao tác với cơ sở dữ liệu ở đây
                    Form_Login.userName = username;
                    Form_Login.password = password;
                    Form_Home home = new Form_Home(username, password);
                    home.show();
                } else {
                    System.out.println("Kết nối đến cơ sở dữ liệu Oracle thất bại.");
                }
            } else {
                System.out.println("Định dạng mã QR không hợp lệ. Không thể trích xuất tên người dùng và mật khẩu.");
            }
        } catch (IOException | NotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi đọc mã QR từ hình ảnh", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private Connection connectToOracle(String database, String username, String password) {
        Connection connection = null;
        try {
            // Xây dựng URL kết nối dựa trên database
            String url = "jdbc:oracle:thin:@localhost:1521:" + database;
            
            // Kết nối đến cơ sở dữ liệu
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);
            JOptionPane.showMessageDialog(null, "Connected to the database", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Kết nối đến cơ sở dữ liệu thất bại. Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);

        }
        return connection;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Class_QRCodeImageReader().setVisible(true);
            }
        });
    }
}
