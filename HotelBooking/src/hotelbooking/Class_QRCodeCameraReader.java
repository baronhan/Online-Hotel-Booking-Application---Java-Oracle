/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author LENOVO
 */
public class Class_QRCodeCameraReader extends JFrame{
    private String username;
    private String password;
    private String userEmail;

    public Class_QRCodeCameraReader(String username, String password) {
        initComponents();
        this.username = username;
        this.password = password;
        // Lưu địa chỉ email người dùng

        // Gửi email xác nhận

    }

    private Webcam webcam = null;
    private WebcamPanel panel = null;

    public Class_QRCodeCameraReader() {
        initComponents();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("QR Code Camera Reader");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        webcam = Webcam.getDefault();
        if (webcam != null) {
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            webcam.open();

            if (webcam.isOpen()) {
                panel = new WebcamPanel(webcam);
                panel.setPreferredSize(WebcamResolution.VGA.getSize());
                mainPanel.add(panel, BorderLayout.CENTER);
            } else {
                JLabel label = new JLabel("Webcam is not open.");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                mainPanel.add(label, BorderLayout.CENTER);
            }
        } else {
            JLabel label = new JLabel("No webcam detected.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(label, BorderLayout.CENTER);
        }

        setContentPane(mainPanel);
    }

    public void startReading() {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Result result = null;
                        BufferedImage image = webcam.getImage();
                        if (image != null) {
                            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
                            try {
                                result = new MultiFormatReader().decode(bitmap);
                            } catch (NotFoundException e) {
                                // QR Code not found in the image
                            }

                            if (result != null) {
                                String qrContent = result.getText();
                                JOptionPane.showMessageDialog(null, "QR Code Detected:\n" + qrContent, "QR Code Detected", JOptionPane.INFORMATION_MESSAGE);
                                // Perform actions based on the QR content
                                // Split the content based on "@" symbol to extract username and password
                                String[] parts = qrContent.split("@");
                                if (parts.length >= 2) {
                                    String username = parts[0];
                                    String password = parts[1];
                                    // Connect to Oracle database
                                    Connection connection = connectToOracle("orcl", username, password);
                                    // Additional actions can be performed here using the database connection
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.setDaemon(true);
        t.start();
    }

    private Connection connectToOracle(String database, String username, String password) {
        Connection connection = null;
        try {
            // Build connection URL based on the database
            String url = "jdbc:oracle:thin:@localhost:1521:" + database;

            // Connect to the database
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(url, username, password);
            JOptionPane.showMessageDialog(null, "Connected to the database", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Tắt camera
            webcam.close();
            JOptionPane.showMessageDialog(null, "Camera is closed", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            // Hiển thị cửa sổ Home
            Form_Login.userName = username;
            Form_Login.password = password;
            Form_Home home = new Form_Home();
            home.setVisible(true);
            home.setLocationRelativeTo(null);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Kết nối đến cơ sở dữ liệu thất bại. Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Class_QRCodeCameraReader reader = new Class_QRCodeCameraReader();
                reader.setVisible(true);
                reader.startReading();
            }
        });
    }

    void setVisible(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
