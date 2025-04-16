package com.mini.payment.listeners;

import com.mini.payment.notify.core.NotifyPersist;
import com.mini.payment.notify.core.NotifyQueue;
import com.mini.payment.domain.notify.service.MpNotifyRecordService;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

public abstract class BaseMessageListener implements MessageListener {

    protected NotifyQueue notifyQueue;
    protected MpNotifyRecordService mpNotifyRecordService;
    protected NotifyPersist notifyPersist;

    public BaseMessageListener(NotifyQueue notifyQueue,
                               MpNotifyRecordService mpNotifyRecordService,
                               NotifyPersist notifyPersist) {
        this.notifyQueue = notifyQueue;
        this.mpNotifyRecordService = mpNotifyRecordService;
        this.notifyPersist = notifyPersist;
    }

    @Override
    public abstract void onMessage(Message message);

}
