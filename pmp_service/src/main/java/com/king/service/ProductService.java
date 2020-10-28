package com.king.service;

import com.king.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(Integer page, Integer size);

    void save(Product product);

}
