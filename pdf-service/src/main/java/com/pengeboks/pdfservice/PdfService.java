package com.pengeboks.pdfservice;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PdfService {

    public void createReceipt(String filename, String userName, String amount, String transactionDate) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);

            contentStream.showText("Receipt for: " + userName);
            contentStream.newLine();
            contentStream.showText("Amount: " + amount);
            contentStream.newLine();
            contentStream.showText("Transaction Date: " + transactionDate);

            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            document.save(filename);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
