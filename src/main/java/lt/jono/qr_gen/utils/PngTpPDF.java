package lt.jono.qr_gen.utils;

import com.itextpdf.text.DocumentException;
import lt.jono.qr_gen.page.WriteQRCodeToPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lt.jono.qr_gen.utils.FileEraser.deleteFiles;

public class PngTpPDF {
    public static void pngTpPDF(int resolution, int marginTop, int marginBottom, int marginLeft, int marginRight) throws DocumentException, IOException {

        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
        List<String> qrCodesList = new ArrayList<>();
        assert files != null;
        for (File file : files) {
            qrCodesList.add(file.getName());
        }
        String outputFileName = "QR_Codes.pdf";
        WriteQRCodeToPage writeQRCodeToPage = new WriteQRCodeToPage();
        writeQRCodeToPage.writeQRCodePDF(qrCodesList, outputFileName, resolution,marginTop,marginBottom,marginLeft,marginRight);
        deleteFiles(qrCodesList);
    }
}
