package com.pengeboks.receipt.controller;

import com.pengeboks.receipt.dto.ReceiptRequest;
import com.pengeboks.receipt.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<String> createAndUploadReceipt(@RequestBody ReceiptRequest request) throws Exception {
        String fileUrl = receiptService.createAndStoreReceipt(request);
        return ResponseEntity.ok(fileUrl);
    }
}
