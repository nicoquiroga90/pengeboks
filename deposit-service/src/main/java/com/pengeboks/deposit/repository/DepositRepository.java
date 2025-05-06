package com.pengeboks.deposit.repository;

import com.pengeboks.deposit.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositRepository extends JpaRepository<Deposit, UUID> {
}