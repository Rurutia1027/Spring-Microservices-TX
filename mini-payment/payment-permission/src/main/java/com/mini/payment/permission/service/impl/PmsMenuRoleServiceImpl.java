package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.permission.repository.PmsMenuRepository;
import com.mini.payment.permission.repository.PmsRoleMenuRepository;
import com.mini.payment.permission.repository.PmsRoleRepository;
import com.mini.payment.permission.service.PmsMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Transactional
@Service("pmsMenuRoleService")
public class PmsMenuRoleServiceImpl implements PmsMenuRoleService {
    @Autowired
    private PmsRoleMenuRepository pmsRoleMenuRepository;

    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Autowired
    private PmsMenuRepository pmsMenuRepository;

    @Override
    public int countMenuByRoleId(Long roleId) {
        return pmsRoleMenuRepository.countByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        pmsRoleMenuRepository.deleteByRoleId(roleId);
    }

    @Override
    public List<PmsRoleMenu> bindRoleMenuNames(Long roleId, Set<String> menuNames) {
        PmsRole pmsRole = pmsRoleRepository.findById(roleId).orElse(null);
        List<PmsRoleMenu> ret = new ArrayList<>();
        if (Objects.isNull(pmsRole)) {
            // bind role item is null
            return ret;
        }

        Set<PmsMenu> pmsMenuSet = pmsMenuRepository.listAllByNames(menuNames);
        if (CollectionUtils.isEmpty(pmsMenuSet)) {
            // bind menu names not matching any items
            return ret;
        }

        // traverse PmsMenuSet and create association items and save
        Set<PmsRoleMenu> pmsRoleMenuSet = new HashSet<>();
        for (PmsMenu item : pmsMenuSet) {
            PmsRoleMenu pmsRoleMenu = new PmsRoleMenu();
            pmsRoleMenu.setMenu(item);
            pmsRoleMenu.setRole(pmsRole);
            pmsRoleMenuSet.add(pmsRoleMenu);
        }

        ret = pmsRoleMenuRepository.saveAll(pmsRoleMenuSet);
        return ret;
    }


    @Override
    public List<PmsRoleMenu> bindRoleMenuIds(Long roleId, Set<Long> menuIds) {
        List<PmsRoleMenu> ret = new ArrayList<>();

        PmsRole pmsRole = pmsRoleRepository.findById(roleId).orElse(null);
        if (Objects.isNull(pmsRole)) {
            // bind role item is null
            return ret;
        }

        Set<PmsMenu> pmsMenuSet = pmsMenuRepository.listAllByIds(menuIds);
        if (CollectionUtils.isEmpty(pmsMenuSet)) {
            // bind menu ids not matching any items
            return ret;
        }

        // traverse PmsMenuSet and create association items and save
        Set<PmsRoleMenu> pmsRoleMenuSet = new HashSet<>();
        for (PmsMenu item : pmsMenuSet) {
            PmsRoleMenu pmsRoleMenu = new PmsRoleMenu();
            pmsRoleMenu.setMenu(item);
            pmsRoleMenu.setRole(pmsRole);
            pmsRoleMenuSet.add(pmsRoleMenu);
        }

        ret = pmsRoleMenuRepository.saveAll(pmsRoleMenuSet);
        return ret;
    }
}
