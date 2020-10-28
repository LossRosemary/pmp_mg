package com.king.service.impl;

import com.github.pagehelper.PageHelper;
import com.king.dao.OrdersDao;
import com.king.domain.Orders;
import com.king.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(Integer page, Integer size) {
        // pageNum：页码；  pageSize：每页显示条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }
}
