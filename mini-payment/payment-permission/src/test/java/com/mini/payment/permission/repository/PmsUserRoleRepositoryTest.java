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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        PmsRole pmsRole = PmsMockUtils.mockPmsRole();

        PmsUser pmsUserRet = pmsUserRepository.save(pmsUser);
        Assertions.assertTrue(pmsUserRet.getId() > 0);

        PmsRole pmsRoleRet = pmsRoleRepository.save(pmsRole);
        Assertions.assertTrue(pmsRoleRet.getId() > 0);

        PmsUserRole pmsUserRole = new PmsUserRole();
        pmsUserRole.setUser(pmsUserRet);
        pmsUserRole.setRole(pmsRoleRet);

        Set<PmsUserRole> pmsUserRoles = new HashSet<>();
        pmsUserRoles.add(pmsUserRole);
        pmsUserRoleRepository.save(pmsUserRole);

        List<PmsUserRole> list1 = pmsUserRoleRepository.findAll();
        pmsUserRepository.deleteById(pmsUser.getId());
        List<PmsUserRole> list2 = pmsUserRoleRepository.findAll();
        Assertions.assertTrue(list1.size() > list2.size());
    }
}