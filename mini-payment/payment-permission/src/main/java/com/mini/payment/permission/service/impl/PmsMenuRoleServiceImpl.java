package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.permission.entity.PmsRolePermission;
import com.mini.payment.permission.repository.PmsRoleMenuRepository;
import com.mini.payment.permission.repository.PmsRolePermissionRepository;
import com.mini.payment.permission.service.PmsMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("pmsMenuRoleService")
public class PmsMenuRoleServiceImpl implements PmsMenuRoleService {
    @Autowired
    private PmsRoleMenuRepository pmsRoleMenuRepository;

    @Autowired
    private PmsRolePermissionRepository pmsRolePermissionRepository;

    @Override
    public int countMenuByRoleId(Long roleId) {
        return 0;
    }

    @Override
    public void deleteByROleId(Long roleId) {

    }

    @Override
    public void saveRoleMenu(Long roleId, Set<String> menuSet) {

    }
}
