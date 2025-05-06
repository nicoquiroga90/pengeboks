package com.pengeboks.receipt.controller;

import com.pengeboks.receipt.dto.ReceiptRequest;
import com.pengeboks.receipt.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<String> createReceipt(@RequestBody ReceiptRequest request) throws Exception {
        // TODO: Add authentication (e.g., Supabase Auth JWT validation)
        String receiptUrl = receiptService.createAndStoreReceipt(request);
        return ResponseEntity.ok(receiptUrl);
    }
}