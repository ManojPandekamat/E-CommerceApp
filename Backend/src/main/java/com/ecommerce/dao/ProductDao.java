package com.ecommerce.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    public List<Product> findAll() {
        return List.of(
                new Product(1L, "Wireless Mouse", new BigDecimal("24.99")),
                new Product(2L, "Mechanical Keyboard", new BigDecimal("79.99")),
                new Product(3L, "USB-C Hub", new BigDecimal("39.99")),
                new Product(4L, "Laptop Stand", new BigDecimal("34.50")),
                new Product(5L, "Webcam", new BigDecimal("59.00")),
                new Product(6L, "Noise-Cancelling Headphones", new BigDecimal("129.99")),
                new Product(7L, "Portable SSD", new BigDecimal("89.95")));
    }
}
