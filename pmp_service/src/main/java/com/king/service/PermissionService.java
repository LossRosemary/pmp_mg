package com.king.service;

import com.king.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    void save(Permission permission);

    Permission findById(String permissionId);

    void deleteById(String permissionId);
}
