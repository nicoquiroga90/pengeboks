package com.pengeboks.deposit.controller;

import com.pengeboks.deposit.client.ReceiptClient;
import com.pengeboks.deposit.dto.ReceiptRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final ReceiptClient receiptClient;

    public DepositController(ReceiptClient receiptClient) {
        this.receiptClient = receiptClient;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDeposit(@RequestParam String fromUser,
                                                @RequestParam String toUser,
                                                @RequestParam double amount,
                                                @RequestParam(required = false) String message) {
        // Simulate deposit creation
        String depositId = UUID.randomUUID().toString();  // Generate a unique deposit ID

        String responseMsg = String.format("Deposit of %.2f DKK from %s to %s created successfully. Deposit ID: %s",
                amount, fromUser, toUser, depositId);

        // Build request to receipt-service
        ReceiptRequest request = new ReceiptRequest(
                fromUser,
                toUser,
                amount,
                depositId,
                message != null ? message : ""
        );

        // Send to receipt-service
        receiptClient.createReceipt(request);

        return ResponseEntity.ok(responseMsg);
    }

    @PostMapping("/release")
    public ResponseEntity<String> releaseDeposit(@RequestParam String depositId) {
        return ResponseEntity.ok("Deposit with ID " + depositId + " has been released.");
    }

    @GetMapping("/status")
    public ResponseEntity<String> checkDepositStatus(@RequestParam String depositId) {
        return ResponseEntity.ok("Deposit " + depositId + " is currently pending release.");
    }
}
