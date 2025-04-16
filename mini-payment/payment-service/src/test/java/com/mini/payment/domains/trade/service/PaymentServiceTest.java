package com.mini.payment.domains.trade.service;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.trade.service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(paymentService);
    }
}
