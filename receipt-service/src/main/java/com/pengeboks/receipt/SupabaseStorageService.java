package com.pengeboks.receipt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import java.time.Duration;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.bucket-name}")
    private String bucketName;

    @Value("${supabase.service-role-key}")
    private String serviceRoleKey;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String upload(byte[] fileBytes, String originalFilename) throws Exception {
        String uniqueFilename = UUID.randomUUID() + "-" + originalFilename;

        String uploadUrl = String.format(
            "%s/storage/v1/object/%s/%s",
            supabaseUrl,
            bucketName,
            uniqueFilename
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uploadUrl))
            .header("Authorization", "Bearer " + serviceRoleKey)
            .header("Content-Type", "application/octet-stream")
            .timeout(Duration.ofSeconds(20))
            .PUT(HttpRequest.BodyPublishers.ofByteArray(fileBytes))
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200 || response.statusCode() == 201) {
            return uniqueFilename;
        } else {
            throw new RuntimeException("Failed to upload file to Supabase: " + response.body());
        }
    }

    public byte[] download(String filename) throws Exception {
        String downloadUrl = String.format(
            "%s/storage/v1/object/%s/%s",
            supabaseUrl,
            bucketName,
            filename
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(downloadUrl))
            .header("Authorization", "Bearer " + serviceRoleKey)
            .timeout(Duration.ofSeconds(20))
            .GET()
            .build();

        HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Failed to download file from Supabase: " + response.statusCode());
        }
    }
}
