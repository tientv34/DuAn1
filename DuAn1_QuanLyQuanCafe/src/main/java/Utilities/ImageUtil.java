/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ImageUtil {
    public static byte[] changeByte(String txt) {

        try {
            BufferedImage bImage = ImageIO.read(new File("src\\main\\java\\image\\" + txt));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos);
            byte[] data = bos.toByteArray();
            return data;

        } catch (IOException ex) {
        }
        return null;
    }

    public static byte[] scaleBytes(byte[] fileData, int width, int height, JFrame frame) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(byteArrayInputStream);
            if (height == 0) {
                height = (width * img.getHeight()) / img.getWidth();
            }
            if (width == 0) {
                width = (height * img.getWidth()) / img.getHeight();
            }
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuffer.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(imageBuffer, "png", byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Lỗi");
        }
        return null;
    }
}
