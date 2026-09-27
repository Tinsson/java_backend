package com.example.demo.service;

import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrderIntegrationTest {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderRepository repository;

    @Test
    void shouldPersistOrderToDatabase() {

        CreateOrderRequest request = new CreateOrderRequest(
                "BTCUSDT",
                new BigDecimal("100"),
                new BigDecimal("2")
        );

        OrderResponse response = service.create(request);

        assertNotNull(response.id());

        assertTrue(
                repository.findById(response.id()).isPresent()
        );
    }
}