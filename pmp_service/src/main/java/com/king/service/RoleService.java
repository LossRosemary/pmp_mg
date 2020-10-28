package com.king.service;

import com.king.domain.Permission;
import com.king.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    void deleteRoleById(String roleId);

    List<Permission> findOtherPermission(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
