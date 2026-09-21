package com.example.demo.model;

public record Order(
        long id,
        String symbol,
        double price,
        double quantity,
        String status
) {
}