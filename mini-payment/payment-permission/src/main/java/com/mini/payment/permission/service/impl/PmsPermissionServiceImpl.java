package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsPermission;
import com.mini.payment.permission.repository.PmsPermissionRepository;
import com.mini.payment.permission.service.PmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service("pmsPermissionService")
public class PmsPermissionServiceImpl implements PmsPermissionService {
    @Autowired
    private PmsPermissionRepository pmsPermissionRepository;

    @Override
    public PmsPermission saveData(PmsPermission pmsPermission) {
        return null;
    }

    @Override
    public PmsPermission updateData(PmsPermission pmsPermission) {
        return null;
    }

    @Override
    public PmsPermission getById(Long id) {
        return null;
    }

    @Override
    public Page<PmsPermission> listPage(PmsPermission pmsPermission, Pageable pageable) {
        return null;
    }

    @Override
    public PmsPermission getByName(String name) {
        return null;
    }

    @Override
    public boolean isPermissionNameAlreadyExist(String pmsName) {
        return false;
    }

    @Override
    public List<PmsPermission> listAllByMenuId(Long menuId) {
        return List.of();
    }

    @Override
    public List<String> findPermissionsByUserId(Long id) {
        return List.of();
    }
}
