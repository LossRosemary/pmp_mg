package com.king.controller;

import com.github.pagehelper.PageInfo;
import com.king.domain.Orders;
import com.king.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

//    // 未分页
//    @RequestMapping("/findAll")
//    public ModelAndView findAll(){
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = ordersService.findAll();
//        mv.addObject("ordersList", ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    @RequestMapping("/findAll")
    @Secured("ROLE_ADMIN")      // 必须要加上前缀ROLE_
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "4") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        // PageInfo 就是一个分页bean
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String id){
        ModelAndView mv = new ModelAndView();
        Orders order = ordersService.findById(id);
        mv.addObject("orders", order);
        mv.setViewName("orders-show");
        return mv;
    }

}
