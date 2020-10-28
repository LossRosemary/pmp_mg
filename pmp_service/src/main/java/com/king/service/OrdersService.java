package com.king.service;

import com.king.domain.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll(Integer page, Integer size);

    Orders findById(String id);

}
