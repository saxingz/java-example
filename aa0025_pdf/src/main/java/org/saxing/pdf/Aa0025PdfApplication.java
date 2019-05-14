package org.saxing.pdf;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.IOException;

public class Aa0025PdfApplication {


    public static void main(String[] args)  throws IOException  {

        PdfDocument pdfDoc =
                new PdfDocument(new PdfReader("D:\\D_desktop\\book\\big2.pdf"), new PdfWriter("D:\\D_desktop\\book\\big4.pdf"));
        Document document = new Document(pdfDoc);
        Rectangle pageSize;
        PdfCanvas canvas;
        int totalPage = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= totalPage; i++) {
            System.out.println("进度： " + i + " / " + totalPage);

            PdfPage page = pdfDoc.getPage(i);
            pageSize = page.getPageSize();
            canvas = new PdfCanvas(page);





        }

        pdfDoc.close();


    }




}
