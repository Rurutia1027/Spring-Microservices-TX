package com.mini.payment.domains.user.service;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.user.service.UserPayWayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class UserPayWayServiceTest {
    @Autowired
    private UserPayWayService userPayWayService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(userPayWayService);
    }
}