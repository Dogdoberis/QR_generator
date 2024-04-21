package lt.jono.qr_gen;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import java.io.IOException;
import java.util.List;

public class
AddQRCodeToPage {


    public static void addQRCodeToPage(Document document,
                                       List<String> qrCodes,
                                       int currentPage,
                                       int qrCodesPerPage,
                                       float resolution) throws IOException, DocumentException {
        // Nustatome puslapio dydį
        Rectangle pageSize = new Rectangle(595, 842); // A4 formatas: 595 x 842 taškai
        document.setPageSize(pageSize);

        // Nustatome puslapio antraštes
        document.setMargins(40, 40, 40, 40);

        // Skaičiuojame, kiek eilučių ir stulpelių tilps į puslapį
        int rows = 10; // 9 eilutės
        int columns = 8; // 7 stulpeliai

//        // Nustatome QR kodų dydį
//        float qrCodeSize =  resolution; // QR kodo dydis taškais

        float horizontalSpace = (pageSize.getWidth() - document.leftMargin() - document.rightMargin() - columns * resolution) / (columns + 1);
        float verticalSpace = (pageSize.getHeight() - document.topMargin() - document.bottomMargin() - rows * resolution) / (rows + 1);

        // Pridedame QR kodus į puslapį
        int start = currentPage * qrCodesPerPage;
        int end = Math.min(start + qrCodesPerPage, qrCodes.size());
        for (int i = start; i < end; i++) {
            int rowIndex = (i - start) / columns;
            int colIndex = (i - start) % columns;
            float x = document.leftMargin() + horizontalSpace + colIndex * (resolution + horizontalSpace);
            float y = document.topMargin() + verticalSpace + (rows - 1 - rowIndex) * (resolution + verticalSpace);

            Image image = Image.getInstance(qrCodes.get(i));
            image.scaleToFit(resolution, resolution);
            image.setAbsolutePosition(x, y);
            document.add(image);
        }
    }

}
