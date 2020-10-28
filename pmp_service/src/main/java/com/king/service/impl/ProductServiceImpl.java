package com.king.service.impl;

import com.github.pagehelper.PageHelper;
import com.king.dao.ProductDao;
import com.king.domain.Product;
import com.king.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service()
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(Integer page, Integer size) {
        // pageNum：页码；  pageSize：每页显示条数
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
