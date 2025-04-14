package com.mini.payment.integration;

import com.mini.payment.MpAppNotifyTestApplication;
import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.config.ConsumerMessageListenerTestConfig;
import com.mini.payment.mock.MockDataRecordUtils;
import com.mini.payment.mock.MockMessage;
import com.mini.payment.mock.MockMessageConverterUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@SpringBootTest(classes = MpAppNotifyTestApplication.class)
@Import(ConsumerMessageListenerTestConfig.class)
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

    @Autowired
    @Qualifier("tradeNotifyListenerContainer")
    private DefaultMessageListenerContainer tradeNotifyListenerContainer;


    @Autowired
    @Qualifier("orderNotifyListenerContainer")
    private DefaultMessageListenerContainer orderNotifyListenerContainer;

    @Autowired
    @Qualifier("merchantNotifyListenerContainer")
    private DefaultMessageListenerContainer merchantNotifyListenerContainer;

    @BeforeEach
    public void initTest() {
        Assertions.assertNotNull(notifyPersist);
        Assertions.assertNotNull(notifyQueue);
        Assertions.assertNotNull(mpNotifyRecordService);
        Assertions.assertNotNull(tradeNotifyJmsTemplate);
        Assertions.assertNotNull(merchantNotifyJmsTemplate);
        Assertions.assertNotNull(orderNotifyJmsTemplate);
        Assertions.assertNotNull(tradeNotifyListenerContainer);
        Assertions.assertNotNull(merchantNotifyListenerContainer);
        Assertions.assertNotNull(orderNotifyListenerContainer);

        // send mock message json string to order notify queue
        orderNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(MockDataRecordUtils.mockOrderMessage())));

        // send mock message json string to merchant notify queue
        merchantNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(MockDataRecordUtils.mockMerchantMessage())));
    }

    @Test
    public void tradeMessageListenerTest() {
        // send mock message to trade notify queue
        MockMessage sendMsg = MockDataRecordUtils.mockTradeMessage();
        tradeNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(sendMsg)));

    }
}
