package lt.jono.qr_gen.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import static lt.jono.qr_gen.utils.DialogBoxHelper.generalEror;


/*Temporarily unused but may be needed in the future at the customer's request.*/


public class PageNumeration extends PdfPageEventHelper {
    private BaseFont baseFont;

    public PageNumeration() {
        try {
            baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            generalEror();
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();
        cb.setFontAndSize(baseFont, 12);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, String.valueOf(writer.getPageNumber()), document.right() - document.rightMargin(), document.bottom()-(-10) , 0);
        cb.endText();
    }
}
