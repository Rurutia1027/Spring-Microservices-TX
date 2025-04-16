package com.mini.payment.domains.account.repository;

import com.mini.payment.MpNotifyApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class MpSettDailyCollectRepositoryTest {
    @Autowired
    private MpSettDailyCollectRepository mpSettDailyCollectRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(mpSettDailyCollectRepository);
    }
}