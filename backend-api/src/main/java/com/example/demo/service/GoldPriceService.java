package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.GoldApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoldPriceService {

    private final WebClient webClient;

    @Value("${goldapi.url}")
    private String apiUrl;

    @Value("${goldapi.token}")
    private String apiToken;

    /**
     * fetches the current gold price from GoldAPI.io.
     * the result is cached for 5 minutes via @Cacheable("goldPrice")
     */
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

            return response.getPrice() / 31.1035;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch gold price from GoldAPI", e);
        }
    }
}
