package com.mini.payment.permission.repository;

import com.mini.payment.PaymentPermissionApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaymentPermissionApplicationTest.class)
public class PmsMenuRepositoryTest {
    @Autowired
    private PmsMenuRepository pmsMenuRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(pmsMenuRepository);
    }
}