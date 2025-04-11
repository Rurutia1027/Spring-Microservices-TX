package com.mini.payment.notify.service.impl;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.notify.service.MpNotifyRecordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class MpNotifyRecordServiceImplTest {
    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(mpNotifyRecordService);
    }
}