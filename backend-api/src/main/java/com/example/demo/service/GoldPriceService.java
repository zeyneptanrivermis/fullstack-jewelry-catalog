package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoldPriceService {

    private final WebClient webClient;

    @Value("${goldapi.url}")
    private String apiUrl;

    @Value("${goldapi.token}")
    private String apiToken;

    @Cacheable("goldPrice")
    public double getGoldPrice() {
        try {
            GoldApiResponse response = webClient.get()
                    .uri(apiUrl)
                    .header("x-access-token", apiToken)
                    .header("Content-Type", "application/json")
                    .retrieve()
                    .bodyToMono(GoldApiResponse.class)
                    .block();

            if (response == null || response.getPrice() == null) {
                throw new RuntimeException("GoldAPI returned null response");
            }

            return response.getPrice();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch gold price from GoldAPI", e);
        }
    }

    private static class GoldApiResponse {
        private Double price;

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
