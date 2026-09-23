package com.example.demo.service;

import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.OrderEntity;
import com.example.demo.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(
            OrderRepository repository
    ) {
        this.repository = repository;
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<OrderResponse> findAllBySymbol(
            String symbol
    ) {
        return repository.findAll()
                .stream()
                .filter(orderEntity -> orderEntity.getSymbol().equals(symbol))
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(long id) {
        return toResponse(getEntity(id));
    }

    @Transactional
    public OrderResponse create(CreateOrderRequest request) {
        OrderEntity order = new OrderEntity(
                request.symbol(),
                request.price(),
                request.quantity()
        );

        OrderEntity saved = repository.save(order);

        return toResponse(saved);
    }

    @Transactional
    public OrderResponse update(
            Long id,
            CreateOrderRequest request
    ) {
        OrderEntity order = getEntity(id);

        order.update(
                request.symbol(),
                request.price(),
                request.quantity()
        );

        OrderEntity saved = repository.save(order);

        return toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        OrderEntity order = getEntity(id);
        repository.delete(order);
    }

    public Page<OrderResponse> search(
            String symbol,
            int page,
            int size
    ) {
        if (page < 0 || size < 1 || size > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid pagination parameters"
            );
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<OrderEntity> result;

        if (symbol == null || symbol.isBlank()) {
            result = repository.findAll(pageable);
        } else {
            result = repository.findBySymbol(symbol.trim(), pageable);
        }

        return result.map(this::toResponse);
    }

    @Transactional
    public void testRollback() {

        OrderEntity order = new OrderEntity(
                "BTCUSDT",
                new BigDecimal("60000"),
                new BigDecimal("0.1")
        );

        repository.save(order);

        throw new RuntimeException("Simulated failure");
    }

    private OrderEntity getEntity(long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Order not found"
                        )
                );
    }

    private OrderResponse toResponse (OrderEntity order) {
        return new OrderResponse(
                order.getId(),
                order.getSymbol(),
                order.getPrice(),
                order.getQuantity(),
                order.getStatus()
        );
    }
}
