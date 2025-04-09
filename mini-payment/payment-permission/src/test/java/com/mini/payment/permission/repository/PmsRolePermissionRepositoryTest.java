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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsRolePermissionRepositoryTest {
    @Autowired
    private PmsRolePermissionRepository pmsRolePermissionRepository;

    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Autowired
    private PmsPermissionRepository pmsPermissionRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsRolePermissionRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsPermission pmsPermission = PmsMockUtils.mockPmsPermission();

        PmsRole pmsRoleRet = pmsRoleRepository.save(pmsRole);
        Assertions.assertNotNull(pmsRoleRet);
        Assertions.assertTrue(pmsRoleRet.getId() > 0);

        PmsPermission pmsPermissionRet = pmsPermissionRepository.save(pmsPermission);
        Assertions.assertNotNull(pmsPermissionRet);
        Assertions.assertTrue(pmsPermissionRet.getId() > 0);

        PmsRolePermission pmsRolePermission = new PmsRolePermission();
        Set<PmsRolePermission> pmsRolePermissionSet = new HashSet<>();
        pmsRoleRet.setRolePermissions(pmsRolePermissionSet);
        pmsPermissionRet.setPermissionRoles(pmsRolePermissionSet);

        pmsRolePermission.setPermission(pmsPermissionRet);
        pmsRolePermission.setRole(pmsRoleRet);
        pmsRolePermission.setCreateTime(new Date());
        PmsRolePermission pmsRolePermissionRet =
                pmsRolePermissionRepository.save(pmsRolePermission);
        Assertions.assertNotNull(pmsRolePermissionRet);
        Assertions.assertNotNull(pmsPermissionRet.getId() > 0);

        PmsRole pmsRoleQueryRet = pmsRoleRepository.findById(pmsRoleRet.getId()).orElse(null);
        Assertions.assertNotNull(pmsRoleQueryRet);
        Assertions.assertTrue(pmsRoleQueryRet.getId() > 0);
        Assertions.assertTrue(pmsRolePermissionRet.getPermission().getId() > 0);
    }
}