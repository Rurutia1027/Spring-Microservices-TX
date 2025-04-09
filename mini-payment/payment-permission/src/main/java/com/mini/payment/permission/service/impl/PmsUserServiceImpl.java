package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.permission.repository.PmsUserRepository;
import com.mini.payment.permission.service.PmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("pmsUserService")
public class PmsUserServiceImpl implements PmsUserService {
    @Autowired
    private PmsUserRepository pmsUserRepository;

    @Override
    public PmsUser saveData(PmsUser pmsUser) {
        return null;
    }

    @Override
    public PmsUser updateData(PmsUser pmsUser) {
        return null;
    }

    @Override
    public PmsUser getById(Long id) {
        return null;
    }

    @Override
    public PmsUser getByLoginName(String loginName) {
        return null;
    }

    @Override
    public Page<PmsUser> listPage(PmsUser pmsUser, Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updatePassword(Long id, String newPwd) {

    }

    @Override
    public PmsUser saveData(PmsUser pmsUser, Set<PmsRole> pmsRoleSet) {
        return null;
    }

    @Override
    public PmsUser updateData(PmsUser pmsUser, Set<PmsRole> pmsRoleSet) {
        return null;
    }
}
