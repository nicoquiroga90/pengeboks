package com.pengeboks.receipt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengeboks.receipt.ReceiptRepository;
import com.pengeboks.receipt.model.Receipt;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Receipt saveReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Optional<Receipt> getReceiptById(Long id) {
        return receiptRepository.findById(id);
    }

    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }

   public List<Receipt> getReceiptsBySender(String fromUser) {
    return receiptRepository.findByFromUser(fromUser);
}

}
