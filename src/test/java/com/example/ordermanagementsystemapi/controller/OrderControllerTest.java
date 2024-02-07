package com.example.ordermanagementsystemapi.controller;

import com.example.ordermanagementsystemapi.exception.OrderNotFoundException;
import com.example.ordermanagementsystemapi.model.Order;
import com.example.ordermanagementsystemapi.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(OrderController.class)


class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldCreateOrder() throws Exception {
        Order order = new Order(1L, "TestOrder");
        Mockito.when(orderService.createOrder(ArgumentMatchers.any(Order.class))).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(order)));
    }

    @Test

    void shouldGetOrderById() throws Exception {

        Order order = new Order(1L, "TestOrder");
        Mockito.when(orderService.getOrderById(1L)).thenReturn(order);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(order)));
    }

    @Test
    void shouldHandleOrderNotFoundExceptionDuringGetOrderById() throws Exception {

        Mockito.when(orderService.getOrderById(2L)).thenThrow(new OrderNotFoundException("Order not found"));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}

