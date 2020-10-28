package com.king.controller;

import com.king.domain.Permission;
import com.king.domain.Role;
import com.king.domain.UserInfo;
import com.king.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String roleId){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(@RequestParam(name = "id") String roleId){
        ModelAndView mv = new ModelAndView();
        roleService.deleteRoleById(roleId);
        return "redirect:findAll";
    }

    // 查询角色以及角色可以添加的权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id") String roleId){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> otherPermissions = roleService.findOtherPermission(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    // 用户添加角色
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId, @RequestParam(name = "ids") String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll";
    }


}
