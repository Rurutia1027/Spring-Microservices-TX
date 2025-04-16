package com.mini.payment.domain.persistence;

import com.mini.payment.MpNotifyApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class PersistenceServiceImplTest {
    @Autowired
    private PersistenceServiceImpl persistenceService;


    @Test
    public void initTest() {
        Assertions.assertNotNull(persistenceService);
        Assertions.assertNotNull(persistenceService.getSessionFactory());
    }
}