package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PmsUserService {
    PmsUser saveData(PmsUser pmsUser);

    PmsUser updateData(PmsUser pmsUser);

    PmsUser getById(Long id);

    PmsUser getByLoginName(String loginName);

    Page<PmsUser> listPage(PmsUser pmsUser, Pageable pageable);

    void deleteById(Long id);

    void updatePassword(Long id, String newPwd);

    // save user and user's associated roles
    PmsUser saveData(PmsUser pmsUser, Set<PmsRole> pmsRoleSet);

    PmsUser updateData(PmsUser pmsUser, Set<PmsRole> pmsRoleSet);
}
