package com.example.ordermanagementsystemapi.service;

import com.example.ordermanagementsystemapi.exception.OrderNotFoundException;
import com.example.ordermanagementsystemapi.model.Order;
import com.example.ordermanagementsystemapi.model.Product;
import com.example.ordermanagementsystemapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        getOrderByIdOrThrow(orderId);

        updatedOrder.setId(orderId);
        return orderRepository.save(updatedOrder);
    }

    public Order addProductToOrder(Long orderId, Product product) {
        Order order = getOrderByIdOrThrow(orderId);

        order.getProducts().add(product);
        return orderRepository.save(order);
    }

    public void removeProductFromOrder(Long orderId, Long productId) {
        Order order = getOrderByIdOrThrow(orderId);

        order.getProducts().removeIf(product -> product.getId().equals(productId));
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        Order order = getOrderByIdOrThrow(orderId);

        orderRepository.deleteById(order.getId());
    }

    private Order getOrderByIdOrThrow(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
