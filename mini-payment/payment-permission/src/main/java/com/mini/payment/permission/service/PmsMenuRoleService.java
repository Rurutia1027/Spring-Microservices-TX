package com.mini.payment.permission.service;

import java.util.Set;

public interface PmsMenuRoleService {
    int countMenuByRoleId(Long roleId);

    void deleteByROleId(Long roleId);

    void saveRoleMenu(Long roleId, Set<String> menuSet);
}
