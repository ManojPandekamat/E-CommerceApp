package com.ecommerce.api;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void getOrdersReturnsOrdersAsJson() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(
                new Order(1L, "Alex Morgan", List.of(1L, 2L), new BigDecimal("104.98")),
                new Order(2L, "Jamie Taylor", List.of(1L), new BigDecimal("24.99"))));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].customerName").value("Alex Morgan"))
                .andExpect(jsonPath("$[0].productIds[0]").value(1))
                .andExpect(jsonPath("$[0].total").value(104.98))
                .andExpect(jsonPath("$[1].customerName").value("Jamie Taylor"));

        verify(orderService).getAllOrders();
    }
}
