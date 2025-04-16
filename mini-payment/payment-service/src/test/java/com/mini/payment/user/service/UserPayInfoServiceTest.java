package com.mini.payment.user.service;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.user.service.UserPayInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class UserPayInfoServiceTest {
    @Autowired
    private UserPayInfoService userPayInfoService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(userPayInfoService);
    }
}