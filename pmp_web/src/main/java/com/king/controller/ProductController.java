package com.king.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.domain.Product;
import com.king.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    // (未分页)查询所有
//    @RequestMapping("/findAll")
//    public String findAll(Model model) throws Exception{
//        List<Product> productList = productService.findAll(page, size);
//        model.addAttribute("productList",productList);
//        return "product-list";
//    }

    // (未分页)查询所有
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")      // 可省略前缀ROLE_，实际的权限是ROLE_ADMIN
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "4") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll(page, size);
        PageInfo<Product> pageInfo = new PageInfo<Product>(productList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    // 产品添加
    @RequestMapping("/save")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }
}
