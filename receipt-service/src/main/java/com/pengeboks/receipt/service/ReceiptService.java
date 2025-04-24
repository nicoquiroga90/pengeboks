package com.pengeboks.receipt.service;

import com.pengeboks.receipt.dto.ReceiptRequest;
import com.pengeboks.receipt.model.Receipt;
import com.pengeboks.receipt.repository.ReceiptRepository;
import com.pengeboks.receipt.service.PdfGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReceiptService {

    private final PdfGenerator pdfGenerator;
    private final SupabaseStorageService storageService;
    private final ReceiptRepository repository;

    public ReceiptService(PdfGenerator pdfGenerator, SupabaseStorageService storageService, ReceiptRepository repository) {
        this.pdfGenerator = pdfGenerator;
        this.storageService = storageService;
        this.repository = repository;
    }

    public String createAndStoreReceipt(ReceiptRequest request) throws Exception {
        byte[] pdfBytes = pdfGenerator.generate(request);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = "receipt_" + timestamp + ".pdf";

        String uploadedFileName = storageService.upload(pdfBytes, fileName);

        Receipt receipt = new Receipt();
        receipt.setDepositId(request.getDepositId());
        receipt.setFileName(uploadedFileName);
        repository.save(receipt);

        return uploadedFileName;
    }
}
