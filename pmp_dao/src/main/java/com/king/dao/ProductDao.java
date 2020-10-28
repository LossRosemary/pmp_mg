package com.king.dao;

import com.king.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    // 查询所有
    List<Product> findAll();

    // 根据 id 查找
    Product findById(String id);

    // 添加产品
    void save(Product product);

}
