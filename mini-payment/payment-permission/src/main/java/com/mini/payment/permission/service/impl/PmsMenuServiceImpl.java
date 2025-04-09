package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.repository.PmsMenuRepository;
import com.mini.payment.permission.service.PmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("pmsMenuServiceImpl")
public class PmsMenuServiceImpl implements PmsMenuService {
    @Autowired
    private PmsMenuRepository pmsMenuRepository;

    @Override
    public void saveData(PmsMenu pmsMenu) {

    }

    @Override
    public PmsMenu updateData(PmsMenu menu) {
        return null;
    }

    @Override
    public List<PmsMenu> listByParentId(Long parentId) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PmsMenu> listByRoleIds(Set<Long> roleIds) {
        return List.of();
    }

    @Override
    public List<PmsMenu> listLeafMenus(String menuName) {
        return List.of();
    }

    @Override
    public PmsMenu getById(Long id) {
        return null;
    }

    @Override
    public Set<Long> getMenuIdsByRoleId(Long roleId) {
        return Set.of();
    }
}
