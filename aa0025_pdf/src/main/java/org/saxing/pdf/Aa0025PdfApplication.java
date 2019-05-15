package org.saxing.pdf;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class Aa0025PdfApplication {

    static final String WECHAT_STR = "hello";
    static final String READER_PATH = "E:\\D_Desktop\\test\\1.pdf";
    static final String OUT_PATH = "E:\\D_Desktop\\test\\2.pdf";

    static final Integer WATER_TIMES_RANGE = 4;
    static final Integer WATER_POSITION_RANGE = 6;
    static final Integer WATER_FONT_SIZE_RANGE = 60;
    static final Float WATER_OPACITY = 0.05f;


    public static void main(String[] args)  throws IOException  {

        byte[] user = "".getBytes();
        byte[] owner = "xyz".getBytes();

//        PdfDocument pdfDoc =
//                new PdfDocument(new PdfReader("D:\\D_desktop\\book\\big2.pdf"),
//                        new PdfWriter("D:\\D_desktop\\book\\big4.pdf",
//                                new WriterProperties()
//                                        .setStandardEncryption(user, owner,
//                                                EncryptionConstants.ALLOW_PRINTING | EncryptionConstants.ALLOW_COPY,
//                                                EncryptionConstants.ENCRYPTION_AES_256)));

        PdfDocument pdfDoc =
                new PdfDocument(new PdfReader(READER_PATH),
                        new PdfWriter(OUT_PATH));

        Document document = new Document(pdfDoc);

        Rectangle pageSize;
        PdfCanvas canvas;
        int totalPage = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= totalPage; i++) {
            System.out.print("进度： " + i + " / " + totalPage + "\t");
            PdfPage page = pdfDoc.getPage(i);
            pageSize = page.getPageSize();
            canvas = new PdfCanvas(page);
            addText2(canvas, pageSize, totalPage, i, document, pdfDoc, page);

        }

        pdfDoc.close();


    }

    private static void addText2(PdfCanvas canvas, Rectangle pageSize, int totalPage, int i, Document document, PdfDocument pdfDoc, PdfPage page) throws IOException{
        //Draw header text
        canvas.beginText().setFontAndSize(
                PdfFontFactory.createFont(FontConstants.HELVETICA), 7)
                .moveText(pageSize.getWidth() / 2 - 24, pageSize.getHeight() - 10)
                .showText(randomStr() + WECHAT_STR + randomStr())
                .endText();
        //Draw footer line
        canvas.setStrokeColor(Color.BLACK)
                .setLineWidth(.2f)
                .moveTo(pageSize.getWidth() / 2 - 30, 20)
                .lineTo(pageSize.getWidth() / 2 + 30, 20).stroke();

        //Draw page number
        canvas.beginText().setFontAndSize(
                PdfFontFactory.createFont(FontConstants.HELVETICA), 7)
                .moveText(pageSize.getWidth() / 2 - 7, 10)
                .showText(randomStr() + WECHAT_STR + randomStr())
                .endText();

        int waterTimes = randomInt(WATER_TIMES_RANGE);
        System.out.println("本页水印: " + waterTimes + "层");
        for (int j = 0; j < waterTimes; j++) {
            //Draw watermark
            Paragraph p = new Paragraph(WECHAT_STR).setFontSize(randomInt(WATER_FONT_SIZE_RANGE));
            PdfExtGState gs1 = new PdfExtGState().setFillOpacity(WATER_OPACITY);
            canvas.setExtGState(gs1);
            document.showTextAligned(p,
                    pageSize.getWidth() / WATER_POSITION_RANGE * randomInt(WATER_POSITION_RANGE) ,
                    pageSize.getHeight() / WATER_POSITION_RANGE * randomInt(WATER_POSITION_RANGE),
                    pdfDoc.getPageNumber(page),
                    TextAlignment.CENTER, VerticalAlignment.MIDDLE, randomInt(360));
            // add random char
            Paragraph p2 = new Paragraph(getRandomWechat(WECHAT_STR)).setFontSize(randomInt(WATER_FONT_SIZE_RANGE));
            PdfExtGState gs2 = new PdfExtGState().setFillOpacity(WATER_OPACITY);
            canvas.setExtGState(gs2);
            document.showTextAligned(p2,
                    pageSize.getWidth() / WATER_POSITION_RANGE * randomInt(WATER_POSITION_RANGE) ,
                    pageSize.getHeight() / WATER_POSITION_RANGE * randomInt(WATER_POSITION_RANGE),
                    pdfDoc.getPageNumber(page),
                    TextAlignment.CENTER, VerticalAlignment.MIDDLE, randomInt(360));

            // transparent
            Paragraph p3 = new Paragraph(WECHAT_STR).setFontSize(randomInt(WATER_FONT_SIZE_RANGE));
            PdfExtGState gs3 = new PdfExtGState().setFillOpacity(0);
            canvas.setExtGState(gs3);
            document.showTextAligned(p3,
                    pageSize.getWidth() / WATER_POSITION_RANGE * randomInt(WATER_POSITION_RANGE) ,
                    pageSize.getHeight() / WATER_POSITION_RANGE * randomInt(WATER_POSITION_RANGE),
                    pdfDoc.getPageNumber(page),
                    TextAlignment.CENTER, VerticalAlignment.MIDDLE, randomInt(360));
        }


        canvas.saveState();
        canvas.restoreState();
    }

    private static String getRandomWechat(String wechatChar){
        String[] split = wechatChar.split("");
        String result = "";
        for (String s : split){
            result += randomChar();
            result += s;
        }
        return result;
    }

    private static String randomStr(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static Integer randomInt(Integer range){
        return new Random().nextInt(range) + 1;
    }

    private static String randomChar(){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return str.split("")[new Random().nextInt(str.length())];
    }


    private static void addText1(PdfCanvas canvas, Rectangle pageSize, int totalPage, int i, Document document, PdfDocument pdfDoc, PdfPage page) throws IOException{
        //Draw header text
        canvas.beginText().setFontAndSize(
                PdfFontFactory.createFont(FontConstants.HELVETICA), 7)
                .moveText(pageSize.getWidth() / 2 - 24, pageSize.getHeight() - 10)
                .showText("I want to believe")
                .endText();
        //Draw footer line
        canvas.setStrokeColor(Color.BLACK)
                .setLineWidth(.2f)
                .moveTo(pageSize.getWidth() / 2 - 30, 20)
                .lineTo(pageSize.getWidth() / 2 + 30, 20).stroke();
        //Draw page number
        canvas.beginText().setFontAndSize(
                PdfFontFactory.createFont(FontConstants.HELVETICA), 7)
                .moveText(pageSize.getWidth() / 2 - 7, 10)
                .showText(String.valueOf(i))
                .showText(" of ")
                .showText(String.valueOf(totalPage))
                .endText();
        //Draw watermark
        Paragraph p = new Paragraph("wechat-diyicaizi").setFontSize(60);
        canvas.saveState();
        PdfExtGState gs1 = new PdfExtGState().setFillOpacity(0.2f);
        canvas.setExtGState(gs1);
        document.showTextAligned(p,
                pageSize.getWidth() / 2, pageSize.getHeight() / 2,
                pdfDoc.getPageNumber(page),
                TextAlignment.CENTER, VerticalAlignment.MIDDLE, 45);
        canvas.restoreState();
    }


}
