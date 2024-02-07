package com.example.ordermanagementsystemapi.exception;
import lombok.Getter;

import lombok.Setter;


@Getter
@Setter

public class OrderNotFoundException extends RuntimeException {
    private Long orderId;

    public OrderNotFoundException(Long orderId) {
        super("Order not found with id: " + orderId);
        this.orderId = orderId;
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
    public void message() {
        throw new OrderNotFoundException("Custom error message");
    }
}

