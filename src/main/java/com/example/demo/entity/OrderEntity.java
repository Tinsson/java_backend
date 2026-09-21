package com.example.demo.entity;

import com.example.demo.entity.OrderStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal price;

    @Column(nullable = false, precision = 20, scale = 8)
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.NEW;

    protected OrderEntity() {}

    public OrderEntity(
            String symbol,
            BigDecimal price,
            BigDecimal quantity
    ) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void update(
            String symbol,
            BigDecimal price,
            BigDecimal quantity
    ) {
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }
}
