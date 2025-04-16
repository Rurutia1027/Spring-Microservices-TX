package com.mini.payment.domain.user.service;

import com.mini.payment.MpNotifyApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class UserBankAccountServiceTest {
    @Autowired
    private UserBankAccountService userBankAccountService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(userBankAccountService);
    }

}