package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsRolePermission;
import com.mini.payment.permission.repository.PmsRolePermissionRepository;
import com.mini.payment.permission.service.PmsRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("pmsRolePermissionService")
public class PmsRolePermissionServiceImpl implements PmsRolePermissionService {
    @Autowired
    private PmsRolePermissionRepository pmsRolePermissionRepository;

    @Override
    public Set<String> getPermissionsByUserId(Long uid) {
        return Set.of();
    }

    @Override
    public PmsRolePermission saveData(PmsRolePermission pmsRolePermission) {
        return null;
    }

    @Override
    public PmsRolePermission updateData(PmsRolePermission pmsRolePermission) {
        return null;
    }

    @Override
    public PmsRolePermission getById(Long id) {
        return null;
    }

    @Override
    public Page<PmsRolePermission> listPage(PmsRolePermission pmsRolePermission, Pageable pageable) {
        return null;
    }

    @Override
    public PmsRolePermission saveRolePermissionBindings(Long rid, Set<String> permission) {
        return null;
    }
}
