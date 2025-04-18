package com.mini.payment.integration;

import com.mini.payment.MpAppNotifyTestApplication;
import com.mini.payment.notify.core.NotifyPersist;
import com.mini.payment.notify.core.NotifyQueue;
import com.mini.payment.domain.notify.entity.MpNotifyRecord;
import com.mini.payment.domain.notify.service.MpNotifyRecordService;
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
    public void tradeMessageListenerTest() throws InterruptedException {
        // send mock message to trade notify queue
        MockMessage sendMsg = MockDataRecordUtils.mockTradeMessage();
        tradeNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(sendMsg)));
        String merchantUUID = sendMsg.getMsgUUID();
        String msgType = sendMsg.getMessageType();

        Thread.sleep(1000L);
        MpNotifyRecord recordRet =
                mpNotifyRecordService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(
                        merchantUUID, merchantUUID, msgType);
        Assertions.assertNotNull(recordRet);
        Assertions.assertEquals(sendMsg.getMsgUUID(), recordRet.getMerchantNo());
        Assertions.assertEquals(sendMsg.getMsgUUID(), recordRet.getMerchantOrderNo());
        Assertions.assertEquals(sendMsg.getMessageType(), recordRet.getNotifyType());
    }

    @Test
    public void merchantMessageListenerTest() throws InterruptedException {
        // send mock message to trade notify queue
        MockMessage sendMsg = MockDataRecordUtils.mockMerchantMessage();
        merchantNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(sendMsg)));
        String merchantUUID = sendMsg.getMsgUUID();
        String msgType = sendMsg.getMessageType();

        // the listener container we created and assigned the message queue address
        // will monitor the coming messages on the queue,
        // once message attached to the queue, it will be fetched by the listener
        // and via onMessage this message handler, messages will be converted into the
        // MpNotifyRecord and invoke db service write to database
        // finally we query the inserted records via the local sent record to verify
        // this flow works as expected
        Thread.sleep(1000L);
        MpNotifyRecord recordRet =
                mpNotifyRecordService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(
                        merchantUUID, merchantUUID, msgType);
        Assertions.assertNotNull(recordRet);
        Assertions.assertEquals(sendMsg.getMsgUUID(), recordRet.getMerchantNo());
        Assertions.assertEquals(sendMsg.getMsgUUID(), recordRet.getMerchantOrderNo());
        Assertions.assertEquals(sendMsg.getMessageType(), recordRet.getNotifyType());
    }

    @Test
    public void orderMessageListenerTest() throws InterruptedException {
        MockMessage sendMsg = MockDataRecordUtils.mockOrderMessage();
        orderNotifyJmsTemplate.send(session -> session
                .createTextMessage(MockMessageConverterUtils
                        .toJsonString(sendMsg)));
        String merchantUUID = sendMsg.getMsgUUID();
        String msgType = sendMsg.getMessageType();

        Thread.sleep(2000);
        MpNotifyRecord recordRet =
                mpNotifyRecordService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(
                        merchantUUID, merchantUUID, msgType);
        Assertions.assertNotNull(recordRet);
        Assertions.assertEquals(sendMsg.getMsgUUID(), recordRet.getMerchantNo());
        Assertions.assertEquals(sendMsg.getMsgUUID(), recordRet.getMerchantOrderNo());
        Assertions.assertEquals(sendMsg.getMessageType(), recordRet.getNotifyType());
    }
}
