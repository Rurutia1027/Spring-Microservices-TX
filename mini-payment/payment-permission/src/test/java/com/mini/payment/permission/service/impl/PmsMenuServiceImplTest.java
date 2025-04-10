package com.mini.payment.permission.service.impl;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.permission.repository.PmsRoleMenuRepository;
import com.mini.payment.permission.service.PmsMenuService;
import com.mini.payment.permission.service.PmsRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsMenuServiceImplTest {
    @Autowired
    private PmsMenuService pmsMenuService;

    @Autowired
    private PmsRoleService pmsRoleService;

    @Autowired
    private PmsRoleMenuRepository pmsRoleMenuRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsMenuService);
        Assertions.assertNotNull(pmsRoleService);
        Assertions.assertNotNull(pmsRoleMenuRepository);
    }

    @Test
    public void testSaveData() {
        PmsMenu pmsMenu = PmsMockUtils.mockPmsMenu();
        PmsMenu pmsMenuRet = pmsMenuService.saveData(pmsMenu);
        Assertions.assertTrue(pmsMenuRet.getId() > 0);
    }

    @Test
    public void testListByParentId() {
        PmsMenu rootMenu = PmsMockUtils.mockPmsMenu();
        rootMenu.setLevel(0L);
        rootMenu.setParent(null);
        rootMenu.setIsLeaf(false);

        PmsMenu rootMenuRet = pmsMenuService.saveData(rootMenu);

        List<PmsMenu> leafMenuList = PmsMockUtils.mockPmsMenus(10);
        Set<Long> saveMenuIdList = new HashSet<>();
        for (PmsMenu item : leafMenuList) {
            item.setIsLeaf(true);
            item.setParent(rootMenuRet);
            item.setLevel(1L);
            PmsMenu saveRet = pmsMenuService.saveData(item);
            saveMenuIdList.add(saveRet.getId());
        }

        List<PmsMenu> queryByParentIdRet = pmsMenuService.listByParentId(rootMenuRet.getId());
        Assertions.assertNotNull(queryByParentIdRet);
        Set<Long> idSet =
                queryByParentIdRet.stream().map(menu -> menu.getId()).collect(Collectors.toSet());
        Assertions.assertEquals(saveMenuIdList, idSet);
    }

    @Test
    public void testListByRoleIds() {
        PmsMenu menu1 = PmsMockUtils.mockPmsMenu();
        PmsMenu menu2 = PmsMockUtils.mockPmsMenu();

        PmsRole role1 = PmsMockUtils.mockPmsRole();
        PmsRole role2 = PmsMockUtils.mockPmsRole();
        PmsRole role3 = PmsMockUtils.mockPmsRole();

        // first save menu
        PmsMenu menuRet1 = pmsMenuService.saveData(menu1);
        PmsMenu menuRet2 = pmsMenuService.saveData(menu2);

        // then save menu associated roles
        PmsRole pmsRoleRet1 = pmsRoleService.saveData(role1);
        PmsRole pmsRoleRet2 = pmsRoleService.saveData(role2);
        PmsRole pmsRoleRet3 = pmsRoleService.saveData(role3);

        // the save role-menu middle items
        PmsRoleMenu pmsRoleMenu1 = new PmsRoleMenu();
        pmsRoleMenu1.setRole(pmsRoleRet1);
        pmsRoleMenu1.setMenu(menuRet1);
        pmsRoleMenu1.setMenu(menuRet2);

        PmsRoleMenu pmsRoleMenu2 = new PmsRoleMenu();
        pmsRoleMenu2.setRole(pmsRoleRet2);
        pmsRoleMenu2.setMenu(menuRet1);

        PmsRoleMenu pmsRoleMenu3 = new PmsRoleMenu();
        pmsRoleMenu3.setRole(pmsRoleRet3);
        pmsRoleMenu3.setMenu(menuRet1);
        pmsRoleMenu3.setMenu(menuRet2);
        pmsRoleMenuRepository.save(pmsRoleMenu1);
        pmsRoleMenuRepository.save(pmsRoleMenu2);
        pmsRoleMenuRepository.save(pmsRoleMenu3);

        // collect menu1, menu2's roleIds to set
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(pmsRoleRet1.getId());
        roleIds.add(pmsRoleRet2.getId());
        roleIds.add(pmsRoleRet3.getId());

        List<PmsMenu> pmsMenus = pmsMenuService.listByRoleIds(roleIds);
        Assertions.assertNotNull(pmsMenus);
        Assertions.assertTrue(pmsMenus.size() == 2);
    }
}