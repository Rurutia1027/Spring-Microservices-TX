package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsAuditLogRepositoryTest {
    @Autowired
    private PmsAuditLogRepository pmsAuditLogRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsAuditLogRepository);
    }
}