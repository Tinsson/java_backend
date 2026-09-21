package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateOrderRequest(
        @NotBlank(message = "Symbol cannot be empty")
        String symbol,

        @NotNull(message = "Price is required")
        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "Price must be greater than 0"
        )
        BigDecimal price,

        @NotNull(message = "Quantity is required")
        @DecimalMin(
                value = "0.0",
                inclusive = false,
                message = "Quantity must be greater than 0"
        )
        BigDecimal quantity
) {
}
