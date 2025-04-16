package com.mini.payment.app.polling;

import com.mini.payment.MpAppOrderPollingTestApplication;
import com.mini.payment.mock.MockDataRecordUtils;
import com.mini.payment.domains.trade.entity.Payment;
import com.mini.payment.domains.trade.service.PaymentManagerService;
import com.mini.payment.domains.trade.service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = MpAppOrderPollingTestApplication.class)
public class OrderPollingRunnerTest {
    @Autowired
    private OrderPollingRunner orderPollingRunner;

    // this is the jms template that configured with connection factory
    // and also the activeMQ's queue name
    @Autowired
    @Qualifier("orderNotifyJmsTemplate")
    private JmsTemplate orderNotifyJmsTemplate;

    @Autowired
    private PaymentManagerService paymentManagerService;

    @Autowired
    private PaymentService paymentService;


    // generate 10 bank order numbers and submit them to active mq's order notify queue
    List<Payment> paymentList = MockDataRecordUtils.mockPayment(10);
    List<Payment> savedPaymentList = new ArrayList<>();

    @BeforeEach
    @Transactional
    public void sendMockBankOrderRecordsToMessageQueue() {
        Assertions.assertNotNull(orderPollingRunner);
        // also verify runner's thread pool and task persist instances also initialized
        // properly
        Assertions.assertNotNull(OrderPollingRunner.cachePollingPersist);
        Assertions.assertNotNull(OrderPollingRunner.cacheThreadPool);
        Assertions.assertNotNull(paymentManagerService);
        Assertions.assertNotNull(orderNotifyJmsTemplate);


        for (Payment payment : paymentList) {
            // we need to save the item to db first
            Payment ret = paymentService.saveData(payment);
            savedPaymentList.add(ret);

            // confirm record save to db successfully
            Assertions.assertTrue(ret.getId() > 0);

            // then we send the bank order no to message mq
            orderNotifyJmsTemplate.send(session -> session.createTextMessage(ret.getBankOrderNo()));
        }

    }


    @Test
    public void pollBankOrderNoViaMessageListener() throws InterruptedException {
        // we leave 6s for message listener subscribe message records(bank order number)
        // from active mq and converted to task, and handle task to task queue
        // and invoke task and query db

        Thread.sleep(10000L);

        for (Payment payment : savedPaymentList) {
            Payment ret = paymentService.findById(payment.getId());
            Assertions.assertNotNull(ret);
            Assertions.assertEquals(ret.getId(), payment.getId());
            // only task processed successfully item's verison value will be accumulated
            // so once we detect the version value increased it means this message record is
            // subscribed and processed via the thread pool's task successfully
            Assertions.assertEquals(ret.getDescription(), "Done");
        }
    }
}