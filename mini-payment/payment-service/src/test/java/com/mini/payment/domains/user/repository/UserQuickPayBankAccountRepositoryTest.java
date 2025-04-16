package com.mini.payment.domains.user.repository;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.user.repository.UserQuickPayBankAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class UserQuickPayBankAccountRepositoryTest {
    @Autowired
    private UserQuickPayBankAccountRepository userQuickPayBankAccountRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(userQuickPayBankAccountRepository);
    }
}