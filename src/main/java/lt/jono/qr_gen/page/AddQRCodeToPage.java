package lt.jono.qr_gen.page;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import lt.jono.qr_gen.utils.QRCodeLayoutHelper;

import java.io.IOException;
import java.util.List;

public class AddQRCodeToPage {

    public static void addQRCodeToPage(Document document,
                                       List<String> qrCodes,
                                       int currentPage,
                                       float resolution,
                                       float marginTop,
                                       float marginBottom,
                                       float marginLeft,
                                       float marginRight) throws IOException, DocumentException {
        // Nustatome puslapio dydį
        Rectangle pageSize = new Rectangle(595, 842); // A4 formatas: 595 x 842 taškai
        document.setPageSize(pageSize);

        // Nustatome puslapio antraštes
       document.setMargins(marginLeft, marginRight, marginTop, marginBottom);

        // Skaičiuojame, kiek eilučių ir stulpelių tilps į puslapį
        int[] rowsAndColumns = QRCodeLayoutHelper.getRowsAndColumns(resolution);
        int rows = rowsAndColumns[0];
        int columns = rowsAndColumns[1];
        int qrCodesPerPage = rows * columns;

        // Nustatome QR kodų dydį
        float horizontalSpace = (pageSize.getWidth() - marginLeft - marginRight - columns * resolution) / (columns + 1);
        float verticalSpace = (pageSize.getHeight() - marginTop - marginBottom - rows * resolution) / (rows + 1);


        // Pridedame QR kodus į puslapį
        int start = currentPage * qrCodesPerPage;
        int end = Math.min(start + qrCodesPerPage, qrCodes.size());
        for (int i = start; i < end; i++) {
            int rowIndex = (i - start) / columns;
            int colIndex = (i - start) % columns;
            float x = marginLeft + colIndex * (resolution + horizontalSpace);
            float y = pageSize.getHeight() - marginTop - (rowIndex + 1) * resolution - rowIndex * verticalSpace;
            Image image = Image.getInstance(qrCodes.get(i));
            image.scaleToFit(resolution, resolution);
            image.setAbsolutePosition(x, y);
            document.add(image);
        }
    }
}
