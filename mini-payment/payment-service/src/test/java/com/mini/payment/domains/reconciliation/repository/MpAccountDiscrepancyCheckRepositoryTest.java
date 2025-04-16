package com.mini.payment.domains.reconciliation.repository;

import com.mini.payment.MpNotifyApplicationTest;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class MpAccountDiscrepancyCheckRepositoryTest  {
    @Autowired
    private MpAccountDiscrepancyCheckRepository mpAccountDiscrepancyCheckRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(mpAccountDiscrepancyCheckRepository);
    }
}