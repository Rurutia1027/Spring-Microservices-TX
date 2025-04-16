package com.mini.payment.domains.trade.repository;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.trade.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(orderRepository);
    }
}