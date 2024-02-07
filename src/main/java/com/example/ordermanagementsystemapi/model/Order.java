package com.example.ordermanagementsystemapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;


@Data
@NoArgsConstructor
@Entity
@NonNull
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Order(long l, String testOrder) {
    }

    public Collection<Product> getProducts() {

        return null;
    }

}
