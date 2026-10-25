package com.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.model.Product;
import com.ecommerce.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void returnsProductsFromRepository() {
        List<Product> products = List.of(
                new Product(1L, "Wireless Mouse", new BigDecimal("24.99")),
                new Product(2L, "Mechanical Keyboard", new BigDecimal("79.99")),
                new Product(3L, "USB-C Hub", new BigDecimal("39.99")),
                new Product(4L, "Laptop Stand", new BigDecimal("34.50")),
                new Product(5L, "Webcam", new BigDecimal("59.00")),
                new Product(6L, "Noise-Cancelling Headphones", new BigDecimal("129.99")),
                new Product(7L, "Portable SSD", new BigDecimal("89.95")));
        when(productRepository.findAll()).thenReturn(products);

        assertEquals(products, productService.getAllProducts());
        verify(productRepository).findAll();
    }
}
