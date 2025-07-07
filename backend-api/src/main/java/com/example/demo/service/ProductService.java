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
 * called every time the endpoint is hit (suitable for small datasets like this
 * study case)
 */

@Service
@RequiredArgsConstructor
public class ProductService {

    private final GoldPriceService goldPriceService;

    /**
     * loads products from JSON, enriches with dynamic price and rating
     */
    public List<Product> getAllProducts() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("products.json");
            List<Product> rawProducts = mapper.readValue(inputStream, new TypeReference<List<Product>>() {
            });
            double goldPrice = goldPriceService.getGoldPrice();

            return rawProducts.stream().map(p -> {
                double priceCalc = (p.getPopularityScore() + 1) * p.getWeight() * goldPrice;
                double roundedPrice = Math.round(priceCalc * 100.0) / 100.0;
                double rating = Math.round(p.getPopularityScore() * 5 * 10.0) / 10.0;

                p.setPrice(roundedPrice);
                p.setRating(rating);
                return p;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load or parse products.json", e);
        }
    }

    /**
     * filters products by price and/or popularity
     */
    public List<Product> getFilteredProducts(Double priceMin, Double priceMax,
            Double popularityMin, Double popularityMax) {
        List<Product> products = getAllProducts();
        return products.stream()
                .filter(p -> {
                    boolean matches = true;
                    if (priceMin != null)
                        matches &= p.getPrice() >= priceMin;
                    if (priceMax != null)
                        matches &= p.getPrice() <= priceMax;
                    if (popularityMin != null)
                        matches &= p.getRating() >= popularityMin;
                    if (popularityMax != null)
                        matches &= p.getRating() <= popularityMax;
                    return matches;
                })
                .collect(Collectors.toList());
    }

}
