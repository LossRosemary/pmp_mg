package com.king.dao;

import com.king.domain.Role;
import com.king.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    // 根据用户名查询用户
    UserInfo findByUsername(String username);

    // 查询所有
    List<UserInfo> findAll();

    // 保存
    void save(UserInfo userInfo);

    // 用户详情
    UserInfo findById(String id);

    // 查询用户以及用户可以添加的角色
    List<Role> findOtherRoles(String userId);

    // 用户添加角色
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
