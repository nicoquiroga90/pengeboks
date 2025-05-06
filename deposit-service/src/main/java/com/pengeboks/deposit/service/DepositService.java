package com.pengeboks.deposit.service;

import com.pengeboks.deposit.dto.ReceiptRequest;
import com.pengeboks.deposit.model.Deposit;
import com.pengeboks.deposit.repository.DepositRepository;
import com.pengeboks.deposit.exception.DepositNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DepositService {

    private final DepositRepository depositRepository;
    private final RestTemplate restTemplate;

    public DepositService(DepositRepository depositRepository, RestTemplate restTemplate) {
        this.depositRepository = depositRepository;
        this.restTemplate = restTemplate;
    }

    public Deposit createDeposit(Deposit deposit) {
        deposit.setCreatedAt(OffsetDateTime.now());
        deposit.setStatus("PENDING");
        Deposit savedDeposit = depositRepository.save(deposit);

        // Create ReceiptRequest
        ReceiptRequest receiptRequest = new ReceiptRequest(
            deposit.getSenderName(),
            deposit.getReceiverName(),
            deposit.getAmount().doubleValue(),
            savedDeposit.getId().toString(),
            deposit.getMessage()
        );

        try {
            // Call ReceiptService
            String receiptUrl = restTemplate.postForObject(
                "http://receipt-service:8083/api/receipts",
                receiptRequest,
                String.class
            );
            savedDeposit.setReceiptUrl(receiptUrl);
            savedDeposit = depositRepository.save(savedDeposit);
        } catch (Exception e) {
            // Log error and continue (receipt generation failed, but deposit is saved)
            System.err.println("Failed to generate receipt: " + e.getMessage());
        }

        return savedDeposit;
    }

    public Deposit getDepositById(UUID id) {
        return depositRepository.findById(id)
                .orElseThrow(() -> new DepositNotFoundException("Deposit not found with id: " + id));
    }

    public List<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    public Deposit updateStatus(UUID id, String status) {
        Deposit deposit = getDepositById(id);
        deposit.setStatus(status.toUpperCase());
        return depositRepository.save(deposit);
    }

    public void deleteDeposit(UUID id) {
        depositRepository.deleteById(id);
    }
}