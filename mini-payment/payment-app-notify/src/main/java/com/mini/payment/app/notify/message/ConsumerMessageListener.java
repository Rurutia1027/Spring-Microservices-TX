package com.mini.payment.app.notify.message;

import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("consumerMessageListener")
public class ConsumerMessageListener extends MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerMessageListener.class);

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Autowired
    private NotifyPersist notifyPersist;


    @Override
    public void onMessage(Message message) {

    }
}
