package com.pengeboks.deposit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptRequest {
    private String fromUser;
    private String toUser;
    private double amount;
    private String depositId;
    private String message;
}
