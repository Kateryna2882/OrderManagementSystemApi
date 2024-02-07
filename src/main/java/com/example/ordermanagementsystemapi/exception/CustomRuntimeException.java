package com.example.ordermanagementsystemapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomRuntimeException {
    String message;
    Long cause;
}
