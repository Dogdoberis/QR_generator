package lt.jono.qr_gen.generator;

import static lt.jono.qr_gen.generator.Generator.generateQRCode;
import static lt.jono.qr_gen.utils.PngTpPDF.pngTpPDF;

public class GenerateQRCodes {

    public static void generateQRCodes(String series, int startNumber, int endNumber, int resolution) {
        for (int i = startNumber; i <= endNumber; i++) {
            generateQRCode(series, i);

        }
        pngTpPDF(resolution);
    }
}
