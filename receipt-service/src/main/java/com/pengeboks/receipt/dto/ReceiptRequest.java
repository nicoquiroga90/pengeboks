package com.pengeboks.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRequest {
    private String fromUser;
    private String toUser;
    private Double amount;
    private String depositId;
    private String message;
}
