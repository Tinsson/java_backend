package com.example.demo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrderCalculationTest {

    @Test
    void shouldCalculateOrderAmount() {

        // Arrange
        BigDecimal price = new BigDecimal("100.5");
        BigDecimal quantity = new BigDecimal("2");

        // Act
        BigDecimal amount = price.multiply(quantity);

        // Assert
        assertEquals(
                0,
                amount.compareTo(new BigDecimal("201.0"))
        );
    }
}
