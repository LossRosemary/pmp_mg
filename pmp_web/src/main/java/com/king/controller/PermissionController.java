package com.king.controller;

import com.king.domain.Permission;
import com.king.domain.Role;
import com.king.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String permissionId){
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(permissionId);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deletePermission")
    public String deletePermission(@RequestParam(name = "id") String permissionId){
        ModelAndView mv = new ModelAndView();
        permissionService.deleteById(permissionId);
        return "redirect:findAll";
    }
}
