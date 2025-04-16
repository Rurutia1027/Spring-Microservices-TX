package com.mini.payment.trade.repository;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.trade.repository.MerchantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class MerchantRepositoryTest {
    @Autowired
    private MerchantRepository merchantRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(merchantRepository);
    }
}