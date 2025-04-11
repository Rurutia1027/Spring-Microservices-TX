package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.repository.PmsRoleRepository;
import com.mini.payment.permission.service.PmsRoleService;
import jakarta.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("pmsRoleService")
public class PmsRoleServiceImpl implements PmsRoleService {
    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Override
    public PmsRole saveData(PmsRole pmsRole) {
        return pmsRoleRepository.save(pmsRole);
    }

    @Override
    public PmsRole updateData(PmsRole pmsRole) {
        return null;
    }

    @Override
    public PmsRole getById(Long id) {
        return pmsRoleRepository.findById(id).orElse(null);
    }


    @Override
    public Optional<PmsRole> findWithMenus(Long id) {
        Specification<PmsRole> spec = (root, query, cb) -> {
            root.fetch("roleMenus", JoinType.LEFT).fetch("menu", JoinType.LEFT);
            query.distinct(true);
            return cb.equal(root.get("id"), id);
        };
        return pmsRoleRepository.findOne(spec);
    }

    @Override
    public Optional<PmsRole> findWithPermissions(Long id) {
        Specification<PmsRole> spec = (root, query, cb) -> {
            root.fetch("rolePermissions", JoinType.LEFT).fetch("permission", JoinType.LEFT);
            query.distinct(true);
            return cb.equal(root.get("id"), id);
        };
        return pmsRoleRepository.findOne(spec);
    }

    @Override
    public Optional<PmsRole> findWithUsers(Long id) {
        Specification<PmsRole> spec = (root, query, cb) -> {
            root.fetch("roleUsers", JoinType.LEFT).fetch("user", JoinType.LEFT);
            query.distinct(true);
            return cb.equal(root.get("id"), id);
        };
        return pmsRoleRepository.findOne(spec);
    }

    @Override
    public Optional<PmsRole> getByIdWithAllRelations(Long id) {
        Specification<PmsRole> spec = (root, query, cb) -> {
            root.fetch("rolePermissions", JoinType.LEFT).fetch("permission", JoinType.LEFT);
            root.fetch("roleMenus", JoinType.LEFT).fetch("menu", JoinType.LEFT);
            root.fetch("roleUsers", JoinType.LEFT).fetch("user", JoinType.LEFT);
            query.distinct(true);
            return cb.equal(root.get("id"), id);
        };
        return pmsRoleRepository.findOne(spec);
    }

    @Override
    public void deleteById(Long id) {
        pmsRoleRepository.deleteById(id);
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
