package com.king.service.impl;

import com.king.dao.RoleDao;
import com.king.domain.Permission;
import com.king.domain.Role;
import com.king.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public void deleteRoleById(String roleId) {
        roleDao.deleteFromUsers_RoleByRoleId(roleId);
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        roleDao.deleteRoleById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
