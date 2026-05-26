package com.example.demo.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// this class used as a DTO between backend layers and for API responses

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String name;
    private double popularityScore;
    private double weight;

    // Key for color and value for image so we used Map 
    private Map<String, String> images;
    private double price;
    private double rating;
}
