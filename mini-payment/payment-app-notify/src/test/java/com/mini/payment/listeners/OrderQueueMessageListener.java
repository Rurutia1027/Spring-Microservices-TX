package com.mini.payment.listeners;

import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import jakarta.jms.Message;

public class OrderQueueMessageListener extends BaseMessageListener {
    public OrderQueueMessageListener(NotifyQueue notifyQueue, MpNotifyRecordService mpNotifyRecordService, NotifyPersist notifyPersist) {
        super(notifyQueue, mpNotifyRecordService, notifyPersist);
    }

    @Override
    public void onMessage(Message message) {

    }
}
