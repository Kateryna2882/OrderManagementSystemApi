package com.example.ordermanagementsystemapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ordermanagementsystemapi.model.Product;
import com.example.ordermanagementsystemapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void shouldGetAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    void shouldGetProductById() throws Exception {
        Product product = new Product(1L, "TestProduct", 10.0);
        Mockito.when(productService.getProductById(1L)).thenReturn((product));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(product)));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = new Product(1L, "TestProduct", 10.0);
        Mockito.when(productService.createProduct(Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(product)));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        Product updatedProduct = new Product(1L, "UpdatedProduct", 15.0);
        Mockito.when(productService.updateProduct(1L, updatedProduct)).thenReturn(updatedProduct);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(updatedProduct)));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
