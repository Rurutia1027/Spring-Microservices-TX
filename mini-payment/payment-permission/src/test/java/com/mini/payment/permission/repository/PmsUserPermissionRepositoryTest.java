package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
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

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsUserPermissionRepository);
    }
}