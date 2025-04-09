package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.PmsMockUtils;
import com.mini.payment.permission.entity.PmsAuditLog;
import com.mini.payment.permission.entity.PmsUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
@Transactional
public class PmsAuditLogRepositoryTest {
    @Autowired
    private PmsAuditLogRepository pmsAuditLogRepository;

    @Autowired
    private PmsUserRepository pmsUserRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsAuditLogRepository);
        Assertions.assertNotNull(pmsUserRepository);
    }

    @Test
    public void saveAndQuery() {
        PmsAuditLog pmsAuditLog = PmsMockUtils.mockPmsAuditLog();
        PmsAuditLog pmsAuditLogRet = pmsAuditLogRepository.save(pmsAuditLog);
        Assertions.assertNotNull(pmsAuditLogRet);
        Assertions.assertTrue(pmsAuditLogRet.getId() > 0);

        PmsAuditLog ret = pmsAuditLogRepository.findById(pmsAuditLogRet.getId()).orElse(null);
        Assertions.assertNotNull(ret);
    }

    @Test
    public void saveWithUser() {
        PmsAuditLog pmsAuditLog = PmsMockUtils.mockPmsAuditLog();
        PmsUser pmsUser = PmsMockUtils.mockPmsUser();
        PmsUser pmsUserRet = pmsUserRepository.save(pmsUser);

        pmsAuditLog.setUser(pmsUserRet);
        List<PmsAuditLog> list = new ArrayList<>();
        list.add(pmsAuditLog);
        pmsUserRet.setAuditLogs(list);

        PmsAuditLog pmsAuditLogRet = pmsAuditLogRepository.save(pmsAuditLog);
        Assertions.assertNotNull(pmsAuditLogRet);
        Assertions.assertTrue(pmsAuditLogRet.getId() > 0);

        PmsUser pmsUserQueryRet = pmsUserRepository.findById(pmsUserRet.getId()).orElse(null);
        Assertions.assertNotNull(pmsUserQueryRet);
        Assertions.assertTrue(pmsUserQueryRet.getAuditLogs().size() > 0);
    }
}