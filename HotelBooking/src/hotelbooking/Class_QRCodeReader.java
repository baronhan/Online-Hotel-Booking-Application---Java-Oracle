/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelbooking;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Class_QRCodeReader {
    public static void main(String[] args) {
        try {
            String imagePath = "D:\\HocTap\\image.png"; // Đường dẫn đến file hình ảnh chứa mã QR

            // Đọc hình ảnh từ file
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Tạo BinaryBitmap từ hình ảnh
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            // Sử dụng MultiFormatReader để đọc mã QR
            Result result = new MultiFormatReader().decode(bitmap);

            // In ra nội dung của mã QR
            System.out.println("Nội dung mã QR: " + result.getText());
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }
}
