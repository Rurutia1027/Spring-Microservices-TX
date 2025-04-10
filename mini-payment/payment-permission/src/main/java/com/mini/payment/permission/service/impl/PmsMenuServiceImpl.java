package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.permission.repository.PmsMenuRepository;
import com.mini.payment.permission.repository.PmsRoleMenuRepository;
import com.mini.payment.permission.repository.PmsRoleRepository;
import com.mini.payment.permission.service.PmsMenuService;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service("pmsMenuServiceImpl")
public class PmsMenuServiceImpl implements PmsMenuService {
    @Autowired
    private PmsMenuRepository pmsMenuRepository;

    @Autowired
    private PmsRoleMenuRepository pmsRoleMenuRepository;

    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Override
    public PmsMenu saveData(PmsMenu pmsMenu) {
        return pmsMenuRepository.save(pmsMenu);
    }

    @Override
    public PmsMenu updateData(PmsMenu menu) {
        return pmsMenuRepository.save(menu);
    }

    @Override
    public List<PmsMenu> listByParentId(Long parentId) {
        return pmsMenuRepository.listByParentId(parentId);
    }

    @Override
    public void delete(Long id) {
        pmsMenuRepository.deleteById(id);
    }

    @Override
    public List<PmsMenu> listByRoleIds(Set<Long> roleIds) {
        Specification<PmsRoleMenu> spec = (root, query, cb) -> {
            Join<PmsRoleMenu, PmsRole> roleJoin = root.join("role", JoinType.INNER);
            root.fetch("menu", JoinType.LEFT);
            query.distinct(true);
            return roleJoin.get("id").in(roleIds);
        };
        List<PmsRoleMenu> pmsRoleMenus = pmsRoleMenuRepository.findAll(spec);
        return pmsRoleMenus.stream()
                .map(PmsRoleMenu::getMenu)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<PmsMenu> listLeafMenus(String menuName) {
        return pmsMenuRepository.findByNameAndLeaf(menuName);
    }

    @Override
    public PmsMenu getById(Long id) {
        return pmsMenuRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Long> getMenuIdsByRoleId(Long roleId) {
        return pmsRoleMenuRepository.findAllIdsByRoleId(roleId);
    }
}
