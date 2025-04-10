package com.mini.payment.permission.service.impl;

import com.mini.payment.PaymentPermissionApplicationTest;
import com.mini.payment.permission.service.PmsUserPermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsUserPermissionServiceImplTest {
    @Autowired
    private PmsUserPermissionService pmsUserPermissionService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsUserPermissionService);
    }
}