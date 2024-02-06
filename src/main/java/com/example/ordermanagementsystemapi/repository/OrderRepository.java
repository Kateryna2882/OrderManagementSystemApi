package com.example.ordermanagementsystemapi.repository;

import com.example.apiordermanagementsystem.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long orderId);


    Order save(Order order);

    void deleteById(Long orderId);
}