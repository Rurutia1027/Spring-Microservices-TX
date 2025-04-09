package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.repository.PmsRoleRepository;
import com.mini.payment.permission.service.PmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("pmsRoleService")
public class PmsRoleServiceImpl implements PmsRoleService {
    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Override
    public PmsRole saveData(PmsRole pmsRole) {
        return null;
    }

    @Override
    public PmsRole updateData(PmsRole pmsRole) {
        return null;
    }

    @Override
    public PmsRole getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Page<PmsRole> listPage(PmsRole pmsRole, Pageable pageable) {
        return null;
    }

    @Override
    public List<PmsRole> listAll() {
        return List.of();
    }

    @Override
    public Set<PmsRole> listByPermissionId(Long permissionId) {
        return Set.of();
    }

    @Override
    public PmsRole getByName(String roleName) {
        return null;
    }

    @Override
    public PmsRole getByCode(String roleCode) {
        return null;
    }
}
