package com.pengeboks.deposit.client;

import com.pengeboks.deposit.dto.ReceiptRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ReceiptClient {

    private final WebClient webClient;

    public ReceiptClient(@Value("${receipt.service.url}") String receiptServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(receiptServiceUrl)
                .build();
    }

    public void createReceipt(ReceiptRequest request) {
        webClient.post()
                .uri("/receipt")
                .body(Mono.just(request), ReceiptRequest.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block(); 
    }
}
