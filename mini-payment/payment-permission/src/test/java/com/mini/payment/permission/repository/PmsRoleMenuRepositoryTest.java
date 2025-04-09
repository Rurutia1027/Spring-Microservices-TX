package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsMenu;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsRoleMenu;
import com.mini.payment.utils.StringUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsRoleMenuRepositoryTest {
    @Autowired
    private PmsRoleMenuRepository pmsRoleMenuRepository;

    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Autowired
    private PmsMenuRepository pmsMenuRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsRoleRepository);
        Assertions.assertNotNull(pmsMenuRepository);
        Assertions.assertNotNull(pmsRoleMenuRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsMenu pmsMenu = PmsMockUtils.mockPmsMenu();
        PmsRoleMenu item = new PmsRoleMenu();
        item.setCreateTime(new Date());
        item.setComment(StringUtil.get36UUID());
        item.setRole(pmsRole);
        item.setMenu(pmsMenu);

        PmsMenu pmsMenuRet = pmsMenuRepository.save(pmsMenu);
        Assertions.assertNotNull(pmsMenuRet);
        Assertions.assertTrue(pmsMenuRet.getId() > 0);

        PmsRole pmsRoleRet = pmsRoleRepository.save(pmsRole);
        Assertions.assertNotNull(pmsRoleRet);
        Assertions.assertTrue(pmsRoleRet.getId() > 0);

        PmsRoleMenu ret = pmsRoleMenuRepository.save(item);
        Assertions.assertNotNull(ret);
        Assertions.assertTrue(ret.getId() > 0);

        List<PmsRoleMenu> itemList = pmsRoleMenuRepository.findAll();
        Assertions.assertTrue(itemList.size() > 0);
    }

    @Test
    public void saveAndDelete() {
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsMenu pmsMenu = PmsMockUtils.mockPmsMenu();
        PmsRoleMenu item = new PmsRoleMenu();
        item.setCreateTime(new Date());
        item.setComment(StringUtil.get36UUID());
        item.setRole(pmsRole);
        item.setMenu(pmsMenu);

        PmsMenu pmsMenuRet = pmsMenuRepository.save(pmsMenu);
        Assertions.assertNotNull(pmsMenuRet);
        Assertions.assertTrue(pmsMenuRet.getId() > 0);

        PmsRole pmsRoleRet = pmsRoleRepository.save(pmsRole);
        Assertions.assertNotNull(pmsRoleRet);
        Assertions.assertTrue(pmsRoleRet.getId() > 0);

        PmsRoleMenu ret = pmsRoleMenuRepository.save(item);
        Assertions.assertNotNull(ret);
        Assertions.assertTrue(ret.getId() > 0);

        // pms_role_menu record cnt
        int cnt1 = pmsRoleMenuRepository.findAll().size();

        // here we delete role and the role's corresponding record should also be deleted
        pmsRoleRepository.deleteById(pmsRoleRet.getId());
        int cnt2 = pmsRoleMenuRepository.findAll().size();

        Assertions.assertTrue(cnt1 > cnt2);
    }
}