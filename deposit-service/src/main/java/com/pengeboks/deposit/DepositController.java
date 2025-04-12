package com.pengeboks.deposit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    /**
     * Endpoint to create a new deposit between two users.
     * @param fromUser The email or ID of the sender
     * @param toUser The email or ID of the receiver
     * @param amount Amount of the deposit
     * @return a confirmation message
     */
    @PostMapping("/create")
    public ResponseEntity<String> createDeposit(@RequestParam String fromUser,
                                                @RequestParam String toUser,
                                                @RequestParam double amount) {
        // Simulate deposit creation
        String message = String.format("Deposit of %.2f DKK from %s to %s created successfully.", amount, fromUser, toUser);
        return ResponseEntity.ok(message);
    }

    /**
     * Endpoint to release a deposit (e.g., after move-in confirmation).
     */
    @PostMapping("/release")
    public ResponseEntity<String> releaseDeposit(@RequestParam String depositId) {
        // Simulate deposit release
        return ResponseEntity.ok("Deposit with ID " + depositId + " has been released.");
    }

    /**
     * Endpoint to check deposit status (simplified for now).
     */
    @GetMapping("/status")
    public ResponseEntity<String> checkDepositStatus(@RequestParam String depositId) {
        // Simulate status check
        return ResponseEntity.ok("Deposit " + depositId + " is currently pending release.");
    }
}
