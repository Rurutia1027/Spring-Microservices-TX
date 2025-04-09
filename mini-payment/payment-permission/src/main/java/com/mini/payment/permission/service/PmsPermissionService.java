package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsPermission;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface PmsPermissionService {
    PmsPermission saveData(PmsPermission pmsPermission);

    PmsPermission updateData(PmsPermission pmsPermission);

    PmsPermission getById(Long id);

    Page<PmsPermission> listPage(PmsPermission pmsPermission, Pageable pageable);

    PmsPermission getByName(String name);

    boolean isPermissionNameAlreadyExist(String pmsName);

    List<PmsPermission> listAllByMenuId(Long menuId);
}
