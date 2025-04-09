package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsMenu;

import java.util.List;
import java.util.Set;

public interface PmsMenuService {
    void saveData(PmsMenu pmsMenu);

    PmsMenu updateData(PmsMenu menu);

    List<PmsMenu> listByParentId(Long parentId);

    void delete(Long id);

    List<PmsMenu> listByRoleIds(Set<Long> roleIds);

    List<PmsMenu> listLeafMenus(String menuName);

    PmsMenu getById(Long id);

    Set<Long> getMenuIdsByRoleId(Long roleId);
}
