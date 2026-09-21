package com.example.demo.dto;

import com.example.demo.entity.OrderStatus;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String symbol,
        BigDecimal price,
        BigDecimal quantity,
        OrderStatus status
) {
}
