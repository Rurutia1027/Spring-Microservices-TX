package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsRoleMenu;

import java.util.List;
import java.util.Set;

public interface PmsMenuRoleService {
    int countMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    List<PmsRoleMenu> bindRoleMenuNames(Long roleId, Set<String> menuNameSet);

    List<PmsRoleMenu> bindRoleMenuIds(Long roleId, Set<Long> menuIdSet);
}
