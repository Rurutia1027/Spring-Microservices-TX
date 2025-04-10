package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PmsRoleService {
    PmsRole saveData(PmsRole pmsRole);

    PmsRole updateData(PmsRole pmsRole);

    PmsRole getById(Long id);

    Optional<PmsRole> findWithMenus(Long id);

    Optional<PmsRole> findWithPermissions(Long id);

    Optional<PmsRole> findWithUsers(Long id);

    Optional<PmsRole> getByIdWithAllRelations(Long id);

    void deleteById(Long id);

    Page<PmsRole> listPage(PmsRole pmsRole, Pageable pageable);

    List<PmsRole> listAll();

    Set<PmsRole> listByPermissionId(Long permissionId);

    PmsRole getByName(String roleName);

    PmsRole getByCode(String roleCode);
}
