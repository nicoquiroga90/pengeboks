package com.pengeboks.receipt.controller;

import com.pengeboks.receipt.dto.ReceiptRequest;
import com.pengeboks.receipt.service.SupabaseStorageService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/receipts/pdf")
public class ReceiptPdfController {

    private final SupabaseStorageService storageService;

    @Autowired
    public ReceiptPdfController(SupabaseStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generatePdfReceipt(@RequestBody ReceiptRequest request) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Deposit Receipt", titleFont));
            document.add(new Paragraph("---------------")); 

            document.add(new Paragraph("From: " + request.getFromUser(), bodyFont));
            document.add(new Paragraph("To: " + request.getToUser(), bodyFont));
            document.add(new Paragraph(String.format("Amount: %.2f DKK", request.getAmount()), bodyFont));
            document.add(new Paragraph("Date: " + LocalDate.now().format(DateTimeFormatter.ISO_DATE), bodyFont));
            document.add(new Paragraph("Deposit ID: " + request.getDepositId(), bodyFont));

            if (request.getMessage() != null && !request.getMessage().isBlank()) {
                document.add(new Paragraph("Message: " + request.getMessage(), bodyFont));
            }

            document.close();

            String fileName = "receipt-" + UUID.randomUUID() + ".pdf";
            String uploadedFile = storageService.upload(outputStream.toByteArray(), fileName);

            return ResponseEntity.ok(uploadedFile);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to generate receipt: " + e.getMessage());
        }
    }
}
