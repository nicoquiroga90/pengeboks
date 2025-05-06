package com.pengeboks.deposit.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private BigDecimal amount;

    private String message;

    @Column(nullable = false)
    private String status;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    // Constructors
    public Deposit() {
    }

    public Deposit(UUID id, String senderName, String receiverName, BigDecimal amount,
                   String message, String status, String receiptUrl, OffsetDateTime createdAt) {
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.amount = amount;
        this.message = message;
        this.status = status;
        this.receiptUrl = receiptUrl;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Builder pattern (manually implemented)
    public static DepositBuilder builder() {
        return new DepositBuilder();
    }

    public static class DepositBuilder {
        private UUID id;
        private String senderName;
        private String receiverName;
        private BigDecimal amount;
        private String message;
        private String status;
        private String receiptUrl;
        private OffsetDateTime createdAt;

        public DepositBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public DepositBuilder senderName(String senderName) {
            this.senderName = senderName;
            return this;
        }

        public DepositBuilder receiverName(String receiverName) {
            this.receiverName = receiverName;
            return this;
        }

        public DepositBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public DepositBuilder message(String message) {
            this.message = message;
            return this;
        }

        public DepositBuilder status(String status) {
            this.status = status;
            return this;
        }

        public DepositBuilder receiptUrl(String receiptUrl) {
            this.receiptUrl = receiptUrl;
            return this;
        }

        public DepositBuilder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Deposit build() {
            return new Deposit(id, senderName, receiverName, amount, message, status, receiptUrl, createdAt);
        }
    }
}