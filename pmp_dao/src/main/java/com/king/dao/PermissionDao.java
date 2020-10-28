package com.king.dao;

import com.king.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    // 查询所有权限
    List<Permission> findAll();

    // 添加权限
    void save(Permission permission);

    // 权限详情
    Permission findById(String permissionId);

    // 删除权限与角色关联
    void deleteFromRole_PermissionByPermissionId(String permissionId);

    // 删除权限
    void deleteById(String permissionId);
}
