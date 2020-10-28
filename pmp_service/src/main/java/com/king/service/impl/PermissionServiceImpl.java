package com.king.service.impl;

import com.king.dao.PermissionDao;
import com.king.domain.Permission;
import com.king.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String permissionId) {
        return permissionDao.findById(permissionId);
    }

    @Override
    public void deleteById(String permissionId) {
        permissionDao.deleteFromRole_PermissionByPermissionId(permissionId);
        permissionDao.deleteById(permissionId);
    }
}
