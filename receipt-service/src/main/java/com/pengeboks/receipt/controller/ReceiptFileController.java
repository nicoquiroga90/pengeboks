package com.pengeboks.receipt.controller;

import com.pengeboks.receipt.service.SupabaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/receipts/files")
public class ReceiptFileController {

    private final SupabaseStorageService storageService;

    @Autowired
    public ReceiptFileController(SupabaseStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadReceiptFile(@RequestParam("file") MultipartFile file) {
        try {
            String filename = storageService.upload(file.getBytes(), file.getOriginalFilename());
            return ResponseEntity.ok(filename);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadReceiptFile(@PathVariable String filename) {
        try {
            byte[] fileBytes = storageService.download(filename);
            return ResponseEntity.ok().body(fileBytes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
