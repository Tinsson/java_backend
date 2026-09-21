package com.example.demo.controller;

import com.example.demo.dto.CreateOrderRequest;
import com.example.demo.dto.OrderResponse;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(
            OrderService service
    ) {
        this.service = service;
    }

    @GetMapping
    public List<OrderResponse> findAll(
            @RequestParam(required = false) String symbol
    ) {
        if (symbol != null) {
            return service.findAllBySymbol(symbol);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponse findById(
            @PathVariable long id
    ) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @Valid @RequestBody CreateOrderRequest request
    ) {
        OrderResponse order = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(order);
    }

    @PostMapping("/test")
    public void testRollback() {
        service.testRollback();
    }

    @PutMapping("/{id}")
    public OrderResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id
    ) {
        service.delete(id);
    }
}
