package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * GET /api/products
     * returns a list of products with dynamically calculated price and rating
     * product data is loaded from JSON and enriched with real-time gold price
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            @RequestParam(required = false) Double popularityMin,
            @RequestParam(required = false) Double popularityMax) {

        if ((priceMin != null && priceMin < 0) || (priceMax != null && priceMax < 0)) {
            return ResponseEntity.badRequest().body(null);
        }

        if ((popularityMin != null && (popularityMin < 0 || popularityMin > 5)) ||
                (popularityMax != null && (popularityMax < 0 || popularityMax > 5))) {
            return ResponseEntity.badRequest().body(null);
        }

        if ((priceMin != null && priceMax != null && priceMin > priceMax) ||
                (popularityMin != null && popularityMax != null && popularityMin > popularityMax)) {
            return ResponseEntity.badRequest().body(null);
        }

        boolean isFiltering = priceMin != null || priceMax != null || popularityMin != null || popularityMax != null;
        List<Product> products;
        if (isFiltering) {
            products = productService.getFilteredProducts(priceMin, priceMax, popularityMin, popularityMax);
        } else {
            products = productService.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

}