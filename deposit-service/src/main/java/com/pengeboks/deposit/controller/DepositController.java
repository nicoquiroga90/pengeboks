package com.pengeboks.deposit.controller;

import com.pengeboks.deposit.model.Deposit;
import com.pengeboks.deposit.service.DepositService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/deposits")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping
    public ResponseEntity<Deposit> createDeposit(@RequestBody Deposit deposit) {
        // TODO: Add authentication (e.g., Supabase Auth JWT validation)
        return ResponseEntity.ok(depositService.createDeposit(deposit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable UUID id) {
        // TODO: Add authentication
        return ResponseEntity.ok(depositService.getDepositById(id));
    }

    @GetMapping
    public ResponseEntity<List<Deposit>> getAllDeposits() {
        // TODO: Add authentication
        return ResponseEntity.ok(depositService.getAllDeposits());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Deposit> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        // TODO: Add authentication
        return ResponseEntity.ok(depositService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable UUID id) {
        // TODO: Add authentication
        depositService.deleteDeposit(id);
        return ResponseEntity.noContent().build();
    }
}