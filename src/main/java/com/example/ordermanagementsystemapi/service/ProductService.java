package com.example.ordermanagementsystemapi.service;

import com.example.ordermanagementsystemapi.exception.ProductNotFoundException;
import com.example.ordermanagementsystemapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong productIdCounter = new AtomicLong(0);

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long productId) {
        return findProductById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public Product createProduct(Product product) {
        product.setId(generateProductId());
        products.add(product);
        return product;
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return existingProduct;
    }

    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        products.remove(product);
    }

    private Optional<Product> findProductById(Long productId) {
        return products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst();
    }

    private Long generateProductId() {
        return productIdCounter.incrementAndGet();
    }
}
