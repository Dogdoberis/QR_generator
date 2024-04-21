package lt.jono.qr_gen.generator;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

import static lt.jono.qr_gen.generator.Generator.generateQRCode;
import static lt.jono.qr_gen.utils.PngTpPDF.pngTpPDF;

public class GenerateQRCodes {

    public static void generateQRCodes(String series,
                                       int startNumber,
                                       int endNumber,
                                       int resolution,
                                       int marginTop,
                                       int marginBottom,
                                       int marginLeft,
                                       int marginRight) throws DocumentException, IOException {
        for (int i = startNumber; i <= endNumber; i++) {
            generateQRCode(series, i);

        }
        pngTpPDF(resolution,marginTop,marginBottom,marginLeft,marginRight);
    }
}
