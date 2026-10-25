package com.ecommerce.repo;

import java.util.List;

import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private final OrderDao orderDao;

    public OrderRepository(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }
}
