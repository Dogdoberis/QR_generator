package lt.jono.qr_gen.page;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static lt.jono.qr_gen.page.AddQRCodeToPage.addQRCodeToPage;


public class WriteQRCodeToPage {



    public  void writeQRCodePDF(List<String> qrCodes, String outputFileName, int resolution) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outputFileName)));
            document.open();


            int qrCodesPerPage = 80;


            int totalPages = (int) Math.ceil((double) qrCodes.size() / qrCodesPerPage);


            for (int currentPage = 0; currentPage < totalPages; currentPage++) {
                document.newPage();


                addQRCodeToPage(document, qrCodes, currentPage, qrCodesPerPage, resolution);
            }

            document.close();
            System.out.println("PDF failas su QR kodais sugeneruotas sėkmingai: " + outputFileName);
        } catch (DocumentException | IOException e) {
            System.out.println("Klaida kuriant PDF failą: " + e.getMessage());
        }

    }

}
