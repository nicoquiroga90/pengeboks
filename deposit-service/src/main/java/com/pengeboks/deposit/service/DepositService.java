package com.pengeboks.deposit.service;

import com.pengeboks.deposit.model.Deposit;
import com.pengeboks.deposit.repository.DepositRepository;
import com.pengeboks.deposit.exception.DepositNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DepositService {

    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public Deposit createDeposit(Deposit deposit) {
deposit.setCreatedAt(OffsetDateTime.now());
        deposit.setStatus("PENDING");
        return depositRepository.save(deposit);
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
