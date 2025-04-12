package com.mini.payment.app.notify.core;

import com.mini.payment.MpAppNotifyTestApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpAppNotifyTestApplication.class)
public class NotifyQueueTest {
    @Autowired
    private NotifyQueue notifyQueue;
    @Test
    public void initTest() {
        Assertions.assertNotNull(notifyQueue);
    }

}