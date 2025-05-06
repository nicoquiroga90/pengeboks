package com.pengeboks.receipt.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptRequest {
    private String fromUser;
    private String toUser;
    private double amount;
    private String message;
    private String depositId;
}