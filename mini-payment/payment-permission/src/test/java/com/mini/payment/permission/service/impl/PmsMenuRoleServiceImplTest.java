package com.mini.payment.permission.service.impl;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.permission.service.PmsMenuRoleService;
import com.mini.payment.permission.service.PmsMenuService;
import com.mini.payment.permission.service.PmsRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsMenuRoleServiceImplTest {
    @Autowired
    private PmsMenuRoleService pmsMenuRoleService;

    @Autowired
    private PmsRoleService pmsRoleService;

    @Autowired
    private PmsMenuService pmsMenuService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsMenuRoleService);
        Assertions.assertNotNull(pmsRoleService);
        Assertions.assertNotNull(pmsMenuService);
    }

    @Test
    public void testCountMenuByRoleId() {
        // create one Role & save to db
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsRole pmsRoleRet = pmsRoleService.saveData(pmsRole);

        // create several menus & save to db
        List<PmsMenu> pmsMenuList = PmsMockUtils.mockPmsMenus(10);
        Set<Long> savedPmsMenuRetIds = new HashSet<>();
        for (PmsMenu item : pmsMenuList) {
            PmsMenu pmsMenuRet = pmsMenuService.saveData(item);
            savedPmsMenuRetIds.add(pmsMenuRet.getId());
        }

        // query role by roleId and checking its associated menu item counts
        List<PmsRoleMenu> pmsRoleMenus = pmsMenuRoleService.bindRoleMenuIds(pmsRoleRet.getId(),
                savedPmsMenuRetIds);
        Assertions.assertTrue(pmsRoleMenus.size() > 0);
        int cnt = pmsMenuRoleService.countMenuByRoleId(pmsRoleRet.getId());
        Assertions.assertTrue(cnt == pmsRoleMenus.size());
    }

    @Test
    public void testDeleteByRoleId() {
        // create one Role & save to db
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsRole pmsRoleRet = pmsRoleService.saveData(pmsRole);

        // create several menus & save to db
        List<PmsMenu> pmsMenuList = PmsMockUtils.mockPmsMenus(10);
        Set<Long> savedPmsMenuRetIds = new HashSet<>();
        for (PmsMenu item : pmsMenuList) {
            PmsMenu pmsMenuRet = pmsMenuService.saveData(item);
            savedPmsMenuRetIds.add(pmsMenuRet.getId());
        }

        // query role by roleId and checking its associated menu item counts
        List<PmsRoleMenu> pmsRoleMenus = pmsMenuRoleService.bindRoleMenuIds(pmsRoleRet.getId(),
                savedPmsMenuRetIds);
        Assertions.assertTrue(pmsRoleMenus.size() > 0);
        int cnt = pmsMenuRoleService.countMenuByRoleId(pmsRoleRet.getId());
        Assertions.assertTrue(cnt == pmsRoleMenus.size());

        // then delete given roleId's corresponding role-menu for db table and then check
        pmsMenuRoleService.deleteByRoleId(pmsRoleRet.getId());
        // then query , the fetched cnt value should be 0
        int cntRet = pmsMenuRoleService.countMenuByRoleId(pmsRoleRet.getId());
        Assertions.assertTrue(cntRet == 0);
    }

    @Test
    public void testBindRoleMenuNames() {
        // create one Role & save to db
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsRole pmsRoleRet = pmsRoleService.saveData(pmsRole);

        // create several menus & save to db
        List<PmsMenu> pmsMenuList = PmsMockUtils.mockPmsMenus(10);
        Set<String> savedPmsMenuRetNames = new HashSet<>();
        for (PmsMenu item : pmsMenuList) {
            PmsMenu pmsMenuRet = pmsMenuService.saveData(item);
            savedPmsMenuRetNames.add(pmsMenuRet.getName());
        }

        List<PmsRoleMenu> bindRet = pmsMenuRoleService.bindRoleMenuNames(pmsRoleRet.getId(),
                savedPmsMenuRetNames);
        Assertions.assertTrue(bindRet.size() > 0);
        PmsRole queryRet = pmsRoleService.findWithMenus(pmsRoleRet.getId()).orElse(null);
        Assertions.assertNotNull(queryRet);
        Assertions.assertTrue(queryRet.getRoleMenus().size() > 0);
        PmsRoleMenu retRoleMenuItem = queryRet.getRoleMenus().stream().findAny().get();
        Assertions.assertTrue(retRoleMenuItem.getMenu() != null && retRoleMenuItem.getRole() != null);
    }

    @Test
    public void testBindingRoleMenuIds() {
        // create one Role & save to db
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsRole pmsRoleRet = pmsRoleService.saveData(pmsRole);

        // create several menus & save to db
        List<PmsMenu> pmsMenuList = PmsMockUtils.mockPmsMenus(10);
        Set<Long> savedPmsMenuRetIds = new HashSet<>();
        for (PmsMenu item : pmsMenuList) {
            PmsMenu pmsMenuRet = pmsMenuService.saveData(item);
            savedPmsMenuRetIds.add(pmsMenuRet.getId());
        }

        List<PmsRoleMenu> bindRet = pmsMenuRoleService.bindRoleMenuIds(pmsRoleRet.getId(),
                savedPmsMenuRetIds);
        Assertions.assertTrue(bindRet.size() > 0);
        PmsRole queryRet = pmsRoleService.findWithMenus(pmsRoleRet.getId()).orElse(null);
        Assertions.assertNotNull(queryRet);
        Assertions.assertTrue(queryRet.getRoleMenus().size() > 0);
        PmsRoleMenu retRoleMenuItem = queryRet.getRoleMenus().stream().findAny().get();
        Assertions.assertTrue(retRoleMenuItem.getMenu() != null && retRoleMenuItem.getRole() != null);
    }
}