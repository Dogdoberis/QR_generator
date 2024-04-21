package lt.jono.qr_gen;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;


public class Generator {
    public static void generateQRCode(String series, int number) {
        String code = series + String.format("%06d", number); // įtraukiame serijos ir numeracijos informaciją į QR kodą
        try {
            ByteArrayOutputStream byteArrayOutputStream = QRCode.from(code).to(ImageType.PNG).stream();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            BufferedImage qrImage = ImageIO.read(new ByteArrayInputStream(byteArray));

            BufferedImage combined = new BufferedImage(qrImage.getWidth(), qrImage.getHeight() + 20, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = combined.createGraphics(); // Sukuriamas Graphics2D objektas

            /*
             *  Įterpiame QR kodą į kombinuotą paveikslėlį
             */

            g.drawImage(qrImage, 0, 0, null);
            /*
             *  Įterpiame serijos ir numeracijos informaciją po QR kodu
             */
            g.setColor(Color.BLACK);// Nustatome teksto spalvą į juodą
            Font font = new Font("Arial", Font.BOLD, 16); //Nustatome teksto šriftą, tipą ir dydį
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(code);
            /*
             * Centruojame tekstą horizontaliai
             */
            int x = (combined.getWidth() - textWidth) / 2;
            int y = qrImage.getHeight() - 2; // Jūs galite keisti vertikalią poziciją pagal poreikį
            g.drawString(code, x, y);

            g.dispose(); // Atlaisviname sukurtais resursais

            File file = new File("QR_" + code + ".png");

            while (file.exists()) {
                file = new File("QR_" + code + ".png");

            }
            /*
             * išsaugojame kombinuotą paveikslėlį su serijos ir numeracijos informacija
             */
            ImageIO.write(combined, "PNG", file);
            out.println("QR kodas su serija " + series + " ir numeracija " + number + " sugeneruotas: " + file.getAbsolutePath());
        } catch (IOException e) {
            out.println("Klaida kuriant QR kodą: " + e.getMessage());
        }

    }
}
