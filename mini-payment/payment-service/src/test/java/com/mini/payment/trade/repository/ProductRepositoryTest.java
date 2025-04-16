package com.mini.payment.trade.repository;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.trade.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(productRepository);
    }
}