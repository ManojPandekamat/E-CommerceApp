package com.ecommerce.repo;

import java.util.List;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final ProductDao productDao;

    public ProductRepository(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }
}
