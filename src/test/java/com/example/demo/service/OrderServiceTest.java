package com.example.demo.service;

import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    @Test
    void shouldCreateOrder(){

        // Arrange
        CreateOrderRequest request = new CreateOrderRequest(
                "BTCUSDT",
                new BigDecimal("100"),
                new BigDecimal("2")
        );

        OrderEntity entity = new OrderEntity(
                "BTCUSDT",
                new BigDecimal("100"),
                new BigDecimal("2")
        );

        when(repository.save(any(OrderEntity.class))).thenReturn(entity);

        // Act
        OrderResponse response = service.create(request);

        // Assert
        assertEquals("BTCUSDT", response.symbol());

        assertEquals(
                0,
                response.price().compareTo(new BigDecimal("100"))
        );

        verify(repository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    void shouldRejectNegativePriceWhenUpdating() {

        CreateOrderRequest request = new CreateOrderRequest(
                "BTCUSDT",
                new BigDecimal("-1"),
                new BigDecimal("2")
        );

        // Act + Assert
        assertThrows(
                ResponseStatusException.class,
                () -> service.update(1L, request)
        );

        verify(repository, never())
                .findById(anyLong());
        verify(repository, never())
                .save(any(OrderEntity.class));
    }
}
