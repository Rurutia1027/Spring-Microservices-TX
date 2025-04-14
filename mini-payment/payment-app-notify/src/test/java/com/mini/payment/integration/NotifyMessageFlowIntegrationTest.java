package com.mini.payment.integration;

import com.mini.payment.MpAppNotifyTestApplication;
import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.mock.MockDataRecordUtils;
import com.mini.payment.mock.MockMessageConverterUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

@SpringBootTest(classes = MpAppNotifyTestApplication.class)
public class NotifyMessageFlowIntegrationTest {
    @Autowired
    private NotifyPersist notifyPersist;

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Autowired
    @Qualifier("merchantNotifyJmsTemplate")
    private JmsTemplate merchantNotifyJmsTemplate;


    @Autowired
    @Qualifier("orderNotifyJmsTemplate")
    private JmsTemplate orderNotifyJmsTemplate;


    @Autowired
    @Qualifier("tradeNotifyJmsTemplate")
    private JmsTemplate tradeNotifyJmsTemplate;

    @Test
    public void initTest() {
        Assertions.assertNotNull(notifyPersist);
        Assertions.assertNotNull(notifyQueue);
        Assertions.assertNotNull(mpNotifyRecordService);
        Assertions.assertNotNull(tradeNotifyJmsTemplate);
        Assertions.assertNotNull(merchantNotifyJmsTemplate);
        Assertions.assertNotNull(orderNotifyJmsTemplate);

        // send mock message to trade notify queue
        tradeNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(MockDataRecordUtils
                                .mockTradeMessage())));

        // send mock message json string to order notify queue
        orderNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(MockDataRecordUtils.mockOrderMessage())));

        // send mock message json string to merchant notify queue
        merchantNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(MockDataRecordUtils.mockMerchantMessage())));
    }
}
