package com.ecommerce.model;

import java.math.BigDecimal;
import java.util.List;

public record Order(Long id, String customerName, List<Long> productIds, BigDecimal total) {
}
