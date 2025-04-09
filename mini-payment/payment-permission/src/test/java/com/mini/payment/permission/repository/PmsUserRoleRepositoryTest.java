package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.permission.entity.PmsUserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsUserRoleRepositoryTest {
    @Autowired
    private PmsUserRoleRepository pmsUserRoleRepository;

    @Autowired
    private PmsUserRepository pmsUserRepository;

    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsUserRoleRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsUser pmsUser = PmsMockUtils.mockPmsUser();
        PmsUser pmsUserRet = pmsUserRepository.save(pmsUser);
        Assertions.assertTrue(pmsUserRet.getId() > 0);

        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsRole pmsRoleRet = pmsRoleRepository.save(pmsRole);
        Assertions.assertTrue(pmsRoleRet.getId() > 0);

        PmsUserRole pmsUserRole = new PmsUserRole();
        pmsUserRole.setRole(pmsRole);
        pmsUserRole.setUser(pmsUser);

        PmsUserRole pmsUserRoleRet = pmsUserRoleRepository.save(pmsUserRole);
        Assertions.assertTrue(pmsUserRet.getId() > 0);
        PmsUserRole pmsUserRoleQueryRet =
                pmsUserRoleRepository.findById(pmsUserRoleRet.getId()).orElse(null);
        Assertions.assertNotNull(pmsUserRoleQueryRet);

        pmsUserRepository.delete(pmsUserRet);
        pmsUserRoleQueryRet =
                pmsUserRoleRepository.findById(pmsUserRoleRet.getId()).orElse(null);
        Assertions.assertNull(pmsUserRoleQueryRet);
    }
}