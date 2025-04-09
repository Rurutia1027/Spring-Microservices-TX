package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.permission.entity.PmsUserRole;

import java.util.List;
import java.util.Set;

public interface PmsUserRoleService {
    Set<Long> getRoleIdsByUserId(Long uid);

    Set<String> getRoleCodesByUserId(Long uid);

    List<PmsUser> listUserByRoleId(Long rid);

    // create user and roles bindings
    PmsUser saveData(PmsUser pmsUser, Set<String> roleNames);

    // update user and role bindings
    PmsUser updateData(PmsUser pmsUser, Set<String> roleNames);

    int countUsersByRoleId(Long roleId);

    // fetch user & role binding items by given user id
    Set<PmsUserRole> listUserRoleBindingsByUserId(Long uid);
}
