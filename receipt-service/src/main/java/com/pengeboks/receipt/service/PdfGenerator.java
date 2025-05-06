package com.pengeboks.receipt.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.pengeboks.receipt.dto.ReceiptRequest;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PdfGenerator {

    public byte[] generate(ReceiptRequest request) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        document.add(new Paragraph("Deposit Receipt", titleFont));
        document.add(new Paragraph("------------------"));

        document.add(new Paragraph("From: " + request.getFromUser(), bodyFont));
        document.add(new Paragraph("To: " + request.getToUser(), bodyFont));
        document.add(new Paragraph(String.format("Amount: %.2f DKK", request.getAmount()), bodyFont));
        document.add(new Paragraph("Date: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE), bodyFont));
        document.add(new Paragraph("Deposit ID: " + request.getDepositId(), bodyFont));

        if (request.getMessage() != null && !request.getMessage().isBlank()) {
            document.add(new Paragraph("Message: " + request.getMessage(), bodyFont));
        }

        document.close();
        return outputStream.toByteArray();
    }
}