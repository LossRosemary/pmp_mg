package com.king.dao;

import com.king.domain.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {

    // 查询所有
    List<Orders> findAll();

    // 根据id查询
    Orders findById(String id);

}
