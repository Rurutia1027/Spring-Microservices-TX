package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsRolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PmsRolePermissionService {
    Set<String> getPermissionsByUserId(Long uid);

    PmsRolePermission saveData(PmsRolePermission pmsRolePermission);

    PmsRolePermission updateData(PmsRolePermission pmsRolePermission);

    PmsRolePermission getById(Long id);

    Page<PmsRolePermission> listPage(PmsRolePermission pmsRolePermission, Pageable pageable);

    PmsRolePermission saveRolePermissionBindings(Long rid, Set<String> permission);
}
