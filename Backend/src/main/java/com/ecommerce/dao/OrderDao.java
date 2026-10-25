package com.ecommerce.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDao {

    public List<Order> findAll() {
        return List.of(
                new Order(1L, "Alex Morgan", List.of(1L, 2L), new BigDecimal("104.98")),
                new Order(2L, "Jamie Taylor", List.of(1L), new BigDecimal("24.99")),
                new Order(3L, "Sam Rivera", List.of(3L, 4L), new BigDecimal("74.49")),
                new Order(4L, "Casey Jordan", List.of(5L), new BigDecimal("59.00")),
                new Order(5L, "Riley Chen", List.of(6L, 7L), new BigDecimal("219.94")),
                new Order(6L, "Taylor Brooks", List.of(2L, 3L), new BigDecimal("119.98")),
                new Order(7L, "Morgan Patel", List.of(1L, 4L, 5L), new BigDecimal("118.49")));
    }
}
