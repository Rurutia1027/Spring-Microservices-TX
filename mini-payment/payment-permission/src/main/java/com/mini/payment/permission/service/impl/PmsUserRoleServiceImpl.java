package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.permission.entity.PmsUserRole;
import com.mini.payment.permission.repository.PmsUserRoleRepository;
import com.mini.payment.permission.service.PmsUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("pmsUserRoleService")
public class PmsUserRoleServiceImpl implements PmsUserRoleService {
    @Autowired
    private PmsUserRoleRepository pmsUserRoleRepository;

    @Override
    public Set<Long> getRoleIdsByUserId(Long uid) {
        return Set.of();
    }

    @Override
    public Set<String> getRoleCodesByUserId(Long uid) {
        return Set.of();
    }

    @Override
    public List<PmsUser> listUserByRoleId(Long rid) {
        return List.of();
    }

    @Override
    public PmsUser saveData(PmsUser pmsUser, Set<String> roleNames) {
        return null;
    }

    @Override
    public PmsUser updateData(PmsUser pmsUser, Set<String> roleNames) {
        return null;
    }

    @Override
    public int countUsersByRoleId(Long roleId) {
        return 0;
    }

    @Override
    public Set<PmsUserRole> listUserRoleBindingsByUserId(Long uid) {
        return Set.of();
    }
}
