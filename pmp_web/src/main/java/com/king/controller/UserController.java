package com.king.controller;

import com.king.domain.Role;
import com.king.domain.UserInfo;
import com.king.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户列表
    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // 只有角色ADMIN才能查询用户
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    // 用户添加
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username == 'king'")   // 只有用户king才能添加用户
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll";
    }

    // 用户详情
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }


    // 查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String userId){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    // 用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId") String userId, @RequestParam(name = "ids") String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll";
    }

}
