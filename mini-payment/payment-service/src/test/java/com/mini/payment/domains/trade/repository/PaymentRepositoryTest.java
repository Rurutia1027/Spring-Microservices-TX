package com.mini.payment.domains.trade.repository;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domains.trade.entity.Payment;
import com.mini.payment.utils.MockDataRecordUtils;
import com.mini.payment.utils.StringUtil;
import com.mini.payment.domains.trade.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(paymentRepository);
    }

    @Test
    public void testFindAllByBankOrderNo() {
        // first we mock data records and save to db
        List<Payment> paymentList = MockDataRecordUtils.mockPayment(10);


        // then save each record's bank order no value
        String sameBankOrderNo = StringUtil.get32UUID();
        for (Payment item : paymentList) {
            item.setBankOrderNo(sameBankOrderNo);
            // then all inserted records share the same bank order number
            Payment ret = paymentRepository.save(item);

            // verify insert success
            Assertions.assertTrue(ret.getId() > 0);
        }
        // then invoke repo's findAllByBankOrderNo function to query orders
        List<Payment> retList = paymentRepository.findAllByBankOrderNo(sameBankOrderNo);
        Assertions.assertTrue(retList.size() > 0);
    }
}