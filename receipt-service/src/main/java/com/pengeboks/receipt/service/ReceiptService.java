package com.pengeboks.receipt.service;

import com.pengeboks.receipt.dto.ReceiptRequest;
import com.pengeboks.receipt.model.Receipt;
import com.pengeboks.receipt.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

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
        String fileName = "receipt-" + request.getDepositId() + ".pdf";
        String uploadedFileName = storageService.upload(pdfBytes, fileName);

        Receipt receipt = new Receipt();
        receipt.setDepositId(request.getDepositId());
        receipt.setFileName(uploadedFileName);
        repository.save(receipt);

        return uploadedFileName;
    }
}
