package com.example.demo.repository;

import com.example.demo.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//import com.example.demo.model.Order;
//import org.springframework.stereotype.Repository;

//import java.util.*;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
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
