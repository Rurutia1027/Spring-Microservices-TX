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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsRoleRepositoryTest {
    @Autowired
    private PmsRoleRepository pmsRoleRepository;

    @Autowired
    private PmsPermissionRepository pmsPermissionRepository;
    @Autowired
    private PmsRolePermissionRepository pmsRolePermissionRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsRoleRepository);
        Assertions.assertNotNull(pmsPermissionRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();
        PmsRole pmsRoleRet = pmsRoleRepository.save(pmsRole);
        Assertions.assertTrue(pmsRoleRet.getId() > 0);

        List<PmsPermission> pmsPermissions = pmsPermissionRepository.findAll();
        Set<PmsRolePermission> pmsRolePermissionSet = new HashSet<>();

        for (PmsPermission item : pmsPermissions) {
            PmsRolePermission pmsRolePermission = new PmsRolePermission();
            pmsRolePermission.setPermission(item);
            pmsRolePermission.setRole(pmsRoleRet);


            PmsRolePermission pmsRolePermissionRet =
                    pmsRolePermissionRepository.save(pmsRolePermission);
            pmsRolePermissionSet.add(pmsRolePermissionRet);
        }

        pmsRoleRet.setRolePermissions(pmsRolePermissionSet);
        PmsRole pmsRoleQueryRet = pmsRoleRepository.findById(pmsRoleRet.getId()).orElse(null);
        Assertions.assertNotNull(pmsRoleQueryRet);
    }
}