package com.example.ordermanagementsystemapi.exception;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long cause) {
        super(String.valueOf(cause));
    }
}
