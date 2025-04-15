package com.pengeboks.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public Receipt createReceipt(@RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }

    @GetMapping
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/{id}")
    public Optional<Receipt> getReceiptById(@PathVariable Long id) {
        return receiptService.getReceiptById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
    }

    @GetMapping("/sender/{sender}")
    public List<Receipt> getReceiptsBySender(@PathVariable String sender) {
        return receiptService.getReceiptsBySender(sender);
    }
}
