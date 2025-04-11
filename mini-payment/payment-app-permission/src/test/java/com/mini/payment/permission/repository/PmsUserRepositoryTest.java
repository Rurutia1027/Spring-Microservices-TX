package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsUserRepositoryTest {
    @Autowired
    private PmsUserRepository pmsUserRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsUserRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsUser pmsUser = PmsMockUtils.mockPmsUser();
        PmsUser pmsUserRet = pmsUserRepository.save(pmsUser);
        Assertions.assertNotNull(pmsUserRet);
        Assertions.assertTrue(pmsUserRet.getId() > 0);
    }

    @Test
    public void saveAndDelete() {
        PmsUser pmsUser = PmsMockUtils.mockPmsUser();
        PmsUser pmsUserRet = pmsUserRepository.save(pmsUser);
        Assertions.assertNotNull(pmsUserRet);
        Assertions.assertTrue(pmsUserRet.getId() > 0);
        pmsUserRepository.delete(pmsUserRet);
        PmsUser pmsUserQueryRet = pmsUserRepository.findById(pmsUserRet.getId()).orElse(null);
        Assertions.assertNull(pmsUserQueryRet);
    }
}