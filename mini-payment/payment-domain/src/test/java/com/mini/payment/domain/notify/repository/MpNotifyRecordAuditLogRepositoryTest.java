package com.mini.payment.domain.notify.repository;

import com.mini.payment.MpNotifyApplicationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class MpNotifyRecordAuditLogRepositoryTest {
    @Autowired
    private MpNotifyRecordAuditLogRepository mpNotifyRecordAuditLogRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(mpNotifyRecordAuditLogRepository);
    }
}