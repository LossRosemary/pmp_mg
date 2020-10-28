package com.king.dao;

import com.king.domain.Permission;
import com.king.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    // 查询所有角色
    List<Role> findAll();

    // 添加角色
    void save(Role role);

    // 角色详情
    Role findById(String roleId);

    // 删除角色与用户关联
    void deleteFromUsers_RoleByRoleId(String roleId);

    // 删除角色与权限关联
    void deleteFromRole_PermissionByRoleId(String roleId);

    // 删除角色
    void deleteRoleById(String roleId);

    // 查询权限以及角色可以添加的权限
    List<Permission> findOtherPermission(String roleId);

    // 角色添加权限
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
