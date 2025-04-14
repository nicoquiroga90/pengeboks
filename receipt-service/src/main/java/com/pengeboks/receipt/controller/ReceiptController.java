package com.pengeboks.receipt.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @PostMapping("/create")
    public String createReceipt(
        @RequestParam String fromUser,
        @RequestParam String toUser,
        @RequestParam double amount
    ) {
        return "Receipt created for " + amount + " from " + fromUser + " to " + toUser;
    }
}
