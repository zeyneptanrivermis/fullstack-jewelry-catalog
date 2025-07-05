package com.example.demo.service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


/**
* loads products from local JSON file, calculates dynamic price and rating
* called every time the endpoint is hit (suitable for small datasets like this study case)
*/

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final GoldPriceService goldPriceService;

    public List<Product> getAllProducts() {
        try {
            // reads JSON file
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("products.json");

            List<Product> rawProducts = mapper.readValue(inputStream, new TypeReference<List<Product>>() {});

            double goldPrice = goldPriceService.getGoldPrice(); 

            // calculates rating and price for each product
            return rawProducts.stream().map(p -> {
                double price = (p.getPopularityScore() + 1) * p.getWeight() * goldPrice;
                double roundedPrice = Math.round(price * 100.0) / 100.0;

                double rating = Math.round(p.getPopularityScore() * 5 * 10.0) / 10.0;

                p.setPrice(roundedPrice);
                p.setRating(rating);

                return p;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load or parse products.json", e);
        }
    }

}
