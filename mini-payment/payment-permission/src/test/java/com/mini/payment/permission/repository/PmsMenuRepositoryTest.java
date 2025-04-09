package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsMenuRepositoryTest {
    @Autowired
    private PmsMenuRepository pmsMenuRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsMenuRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsMenu pmsMenu = PmsMockUtils.mockPmsMenu();
        PmsMenu pmsMenuRet = pmsMenuRepository.save(pmsMenu);
        Assertions.assertNotNull(pmsMenuRet);
        Assertions.assertNotNull(pmsMenuRet.getId() > 0);
    }

    @Test
    public void saveAndDelete() {
        PmsMenu pmsMenu = PmsMockUtils.mockPmsMenu();
        PmsMenu pmsMenuRet = pmsMenuRepository.save(pmsMenu);
        Assertions.assertTrue(pmsMenuRet.getId() > 0);
        pmsMenuRepository.delete(pmsMenuRet);
        PmsMenu pmsMenuQueryRet = pmsMenuRepository.findById(pmsMenuRet.getId()).orElse(null);
        Assertions.assertNull(pmsMenuQueryRet);
    }

    @Test
    public void saveAndQueryChildren() {
        PmsMenu rootMenu = PmsMockUtils.mockPmsMenu();
        List<PmsMenu> children = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PmsMenu item = PmsMockUtils.mockPmsMenu();
            item.setLevel(1L);
            item.setChildren(null);
            item.setIsLeaf(true);
            children.add(item);
        }
        rootMenu.setChildren(children);
        rootMenu.setLevel(0L);
        rootMenu.setIsLeaf(false);
        PmsMenu ret = pmsMenuRepository.save(rootMenu);
        Assertions.assertNotNull(ret);
        Assertions.assertTrue(ret.getId() > 0 && ret.getChildren().size() > 0);
    }
}
