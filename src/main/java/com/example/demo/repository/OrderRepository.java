package com.example.demo.repository;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import com.example.demo.model.Order;
//import org.springframework.stereotype.Repository;

//import java.util.*;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Page<OrderEntity> findBySymbol(
            String symbol,
            Pageable pageable
    );

    List<OrderEntity> findBySymbolAndStatus(
            String symbol,
            OrderStatus status
    );

    List<OrderEntity> findBySymbolOrderByIdDesc(
            String symbol
    );
}



//@Repository
//public class OrderRepository {
//    private final Map<Long, Order> orders = new HashMap<>();
//
//    public void save(Order order) {
//        orders.put(order.id(), order);
//    }
//
//    public Optional<Order> findById(
//            long id
//    ) {
//        return Optional.ofNullable(
//                orders.get(id)
//        );
//    }
//
//    public List<Order> findAll() {
//        return new ArrayList<>(
//                orders.values()
//        );
//    }
//}
