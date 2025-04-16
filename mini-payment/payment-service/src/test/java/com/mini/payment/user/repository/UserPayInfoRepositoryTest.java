package com.mini.payment.user.repository;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.user.repository.UserPayInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class UserPayInfoRepositoryTest {
    @Autowired
    private UserPayInfoRepository userPayInfoRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(userPayInfoRepository);
    }

}