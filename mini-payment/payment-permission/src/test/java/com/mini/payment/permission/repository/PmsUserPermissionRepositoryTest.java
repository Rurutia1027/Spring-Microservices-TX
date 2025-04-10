package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsPermission;
import com.mini.payment.permission.entity.PmsUser;
import com.mini.payment.permission.entity.PmsUserPermission;
import com.mini.payment.permission.service.PmsPermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsUserPermissionRepositoryTest {
    @Autowired
    private PmsUserPermissionRepository pmsUserPermissionRepository;

    @Autowired
    private PmsUserRepository pmsUserRepository;

    @Autowired
    private PmsPermissionService pmsPermissionService;
    @Autowired
    private PmsPermissionRepository pmsPermissionRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsUserPermissionRepository);
        Assertions.assertNotNull(pmsUserRepository);
        Assertions.assertNotNull(pmsPermissionService);
    }

    @Test
    public void saveAndQuery() {
        PmsUser pmsUser = PmsMockUtils.mockPmsUser();
        PmsPermission pmsPermission = PmsMockUtils.mockPmsPermission();

        // save user
        PmsUser pmsUserRet = pmsUserRepository.save(pmsUser);
        Assertions.assertTrue(pmsUserRet.getId() > 0);

        // save permission
        PmsPermission pmsPermissionRet = pmsPermissionRepository.save(pmsPermission);
        Assertions.assertTrue(pmsPermission.getId() > 0);

        // create pms user-permission entity and save
        PmsUserPermission pmsUserPermission = new PmsUserPermission();
        pmsUserPermission.setPermission(pmsPermissionRet);
        pmsUserPermission.setUser(pmsUserRet);

        PmsUserPermission pmsUserPermissionRet =
                pmsUserPermissionRepository.save(pmsUserPermission);
        Assertions.assertTrue(pmsUserPermissionRet.getId() > 0);
    }

    @Test
    @Transactional
    public void saveAndDelete() {
        PmsUser pmsUser = PmsMockUtils.mockPmsUser();
        PmsPermission pmsPermission = PmsMockUtils.mockPmsPermission();

        // save user
        PmsUser pmsUserRet = pmsUserRepository.save(pmsUser);
        Assertions.assertTrue(pmsUserRet.getId() > 0);

        // save permission
        PmsPermission pmsPermissionRet = pmsPermissionRepository.save(pmsPermission);
        Assertions.assertTrue(pmsPermission.getId() > 0);

        // create pms user-permission entity and save
        PmsUserPermission pmsUserPermission = new PmsUserPermission();
        pmsUserPermission.setPermission(pmsPermissionRet);
        pmsUserPermission.setUser(pmsUserRet);

        PmsUserPermission pmsUserPermissionRet =
                pmsUserPermissionRepository.save(pmsUserPermission);
        Assertions.assertTrue(pmsUserPermissionRet.getId() > 0);

        pmsUserPermissionRepository.deleteById(pmsUserPermission.getId());
        pmsUserRepository.deleteById(pmsUserRet.getId());
        pmsPermissionRepository.deleteById(pmsPermissionRet.getId());

        PmsUserPermission queryRet =
                pmsUserPermissionRepository.findById(pmsUserPermissionRet.getId()).orElse(null);
        Assertions.assertNull(queryRet);
    }
}