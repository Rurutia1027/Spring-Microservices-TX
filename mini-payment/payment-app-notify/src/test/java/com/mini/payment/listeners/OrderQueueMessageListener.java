package com.mini.payment.listeners;

import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.mock.MockMessage;
import com.mini.payment.mock.MockMessageConverterUtils;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;

@Component
public class OrderQueueMessageListener extends BaseMessageListener {
    private static final Logger LOG =
            LoggerFactory.getLogger(OrderQueueMessageListener.class);

    private DelayQueue<MockMessage> messageDelayQueue;

    public OrderQueueMessageListener(NotifyQueue notifyQueue, MpNotifyRecordService mpNotifyRecordService, NotifyPersist notifyPersist) {
        super(notifyQueue, mpNotifyRecordService, notifyPersist);
    }

    @Override
    public void onMessage(Message message) {
        try {
            String json = ((TextMessage) message).getText();
            MockMessage msg = MockMessageConverterUtils.toJavaObj(json);
            messageDelayQueue.offer(msg);
        } catch (JMSException e) {
            LOG.error("Got jmx exception ", e);
        }
    }
}
