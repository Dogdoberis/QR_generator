package lt.jono.qr_gen.generator;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import static lt.jono.qr_gen.generator.Config.*;
import static lt.jono.qr_gen.utils.DialogBoxHelper.qrGeneratedError;

public class Generator {


    public static void generateQRCode(String series, int number) {
        String code = series + String.format("%06d", number); // įtraukiame serijos ir numeracijos informaciją į QR kodą
        try {
            ByteArrayOutputStream byteArrayOutputStream = QRCode.from(code).to(ImageType.PNG).stream();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            BufferedImage qrImage = ImageIO.read(new ByteArrayInputStream(byteArray));

            int totalWidth = qrWidth + 2 * frameWidth;
            int totalHeight = qrHeight + 2 * frameWidth + textHeight + 2 * margin;

            BufferedImage combined = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = combined.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, totalWidth, totalHeight);

            Shape clip = new RoundRectangle2D.Float(frameWidth, frameWidth, qrWidth, qrHeight, arcWidth, arcHeight);
            g.setClip(clip);
            g.drawImage(qrImage, frameWidth, frameWidth, qrWidth, qrHeight, null);
            g.setClip(null);

            // Rėmelis
            g.setColor(Color.BLACK);
            g.draw(new RoundRectangle2D.Float(frameWidth, frameWidth, qrWidth, qrHeight + margin + textHeight, arcWidth, arcHeight));

            // Serijos ir numeracijos informacija
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, fontSize);
            g.setFont(font);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);
            FontMetrics fm = g.getFontMetrics();
            int textX = (totalWidth - fm.stringWidth(code)) / 2;
            int textY = frameWidth + qrHeight + margin + fm.getAscent();

            g.drawString(code, textX, textY);

            g.dispose();

            File file = new File("QR_" + code + ".png");

            while (file.exists()) {
                file = new File("QR_" + code + ".png");
            }

            ImageIO.write(combined, "PNG", file);
        } catch (IOException e) {
            qrGeneratedError(e.getMessage());
        }
    }
}