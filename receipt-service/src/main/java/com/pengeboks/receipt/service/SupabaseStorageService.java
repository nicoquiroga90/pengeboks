package com.pengeboks.receipt.service;

import com.pengeboks.receipt.exception.UploadException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;

@Service
public class SupabaseStorageService {

    private final String supabaseUrl = System.getProperty("SUPABASE_URL");
    private final String bucketName = System.getProperty("SUPABASE_BUCKET_NAME");
    private final String serviceRoleKey = System.getProperty("SUPABASE_SERVICE_ROLE_KEY");

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String upload(byte[] fileBytes, String originalFilename) {
        String uploadUrl = String.format(
            "%s/storage/v1/object/%s/%s",
            supabaseUrl,
            bucketName,
            originalFilename
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uploadUrl))
            .header("Authorization", "Bearer " + serviceRoleKey)
            .header("Content-Type", "application/octet-stream")
            .timeout(Duration.ofSeconds(20))
            .PUT(HttpRequest.BodyPublishers.ofByteArray(fileBytes))
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 || response.statusCode() == 201) {
                return String.format("%s/storage/v1/object/public/%s/%s", supabaseUrl, bucketName, originalFilename);
            } else {
                throw new UploadException("Upload failed: " + response.body());
            }
        } catch (Exception e) {
            throw new UploadException("Failed to upload to Supabase", e);
        }
    }
}