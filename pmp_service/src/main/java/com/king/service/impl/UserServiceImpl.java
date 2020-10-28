package com.king.service.impl;

import com.king.dao.UserDao;
import com.king.domain.Role;
import com.king.domain.UserInfo;
import com.king.service.UserService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);

        // 将用户对象封装为UserDetails，User为UserDetails的实现类
        // User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(userInfo.getRoles()));
        User user = new User(
                userInfo.getUsername(),
                // 密码加密
                userInfo.getPassword(),
                userInfo.getStatus() == 1,
                true,
                true,
                true,
                getAuthority(userInfo.getRoles()));
        return user;
    }


    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }


    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }


    @Override
    public void save(UserInfo userInfo) {
        // 对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }


    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }
}
