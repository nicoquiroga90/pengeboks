package com.pengeboks.deposit.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "deposits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
