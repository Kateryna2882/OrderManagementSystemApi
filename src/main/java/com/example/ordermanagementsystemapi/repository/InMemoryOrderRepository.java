package com.example.ordermanagementsystemapi.repository;


import com.example.apiordermanagementsystem.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class  InMemoryOrderRepository implements OrderRepository {

    private final Map<Long, Order> orders = new HashMap<>();

    @Override
    public Optional<Order> findById(Long orderId) {

        return Optional.ofNullable(orders.get(orderId));
    }

    @Override
    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public void deleteById(Long orderId) {

        orders.remove(orderId);
    }


}
