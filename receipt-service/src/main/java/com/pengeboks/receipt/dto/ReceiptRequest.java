package com.pengeboks.receipt.dto;

import lombok.*;

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
