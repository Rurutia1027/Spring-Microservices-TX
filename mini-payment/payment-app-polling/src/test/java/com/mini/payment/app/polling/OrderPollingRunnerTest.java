package com.mini.payment.app.polling;

import com.mini.payment.MpAppOrderPollingTestApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpAppOrderPollingTestApplication.class)
public class OrderPollingRunnerTest {
    @Autowired
    private OrderPollingRunner orderPollingRunner;

    @Test
    public void initTest() {
        Assertions.assertNotNull(orderPollingRunner);
    }
}