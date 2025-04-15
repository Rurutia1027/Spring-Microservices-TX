package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsPermission;
import com.mini.payment.permission.entity.PmsRole;
import com.mini.payment.permission.entity.PmsRolePermission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsPermissionRepositoryTest {
    @Autowired
    private PmsPermissionRepository pmsPermissionRepository;

    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Autowired
    private PmsRolePermissionRepository pmsRolePermissionRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsPermissionRepository);
        Assertions.assertNotNull(pmsRoleRepository);
        Assertions.assertNotNull(pmsRolePermissionRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsPermission pmsPermission = PmsMockUtils.mockPmsPermission();
        PmsPermission pmsPermissionRet = pmsPermissionRepository.save(pmsPermission);
        Assertions.assertNotNull(pmsPermissionRet);
        Assertions.assertTrue(pmsPermissionRet.getId() > 0);
    }

    @Test
    public void saveBindingsAndQuery() {
        PmsPermission pmsPermission = PmsMockUtils.mockPmsPermission();
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();

        PmsRole pmsRoleRet = pmsRoleRepository.save(pmsRole);
        Assertions.assertNotNull(pmsRoleRet);
        Assertions.assertTrue(pmsRoleRet.getId() > 0);

        PmsPermission pmsPermissionRet = pmsPermissionRepository.save(pmsPermission);
        Assertions.assertNotNull(pmsPermissionRet);
        Assertions.assertTrue(pmsPermissionRet.getId() > 0);

        PmsRolePermission pmsRolePermission = new PmsRolePermission();
        pmsRolePermission.setPermission(pmsPermissionRet);
        pmsRolePermission.setRole(pmsRoleRet);

        PmsRolePermission pmsRolePermissionRet=
                pmsRolePermissionRepository.save(pmsRolePermission);
        Assertions.assertNotNull(pmsRolePermissionRet);
    }
}
