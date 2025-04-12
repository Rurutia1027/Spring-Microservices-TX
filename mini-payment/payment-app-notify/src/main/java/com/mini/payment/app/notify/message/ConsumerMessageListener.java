package com.mini.payment.app.notify.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.enums.NotifyStatusEnum;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.exception.BizException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

@Component("consumerMessageListener")
public class ConsumerMessageListener implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerMessageListener.class);

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Autowired
    private NotifyPersist notifyPersist;


    @Override
    public void onMessage(Message message) {
        try {
            ActiveMQTextMessage activeMQMsg = (ActiveMQTextMessage) message;
            final String msg = activeMQMsg.getText();
            LOG.info("Receive msg {}", msg);
            JSON json = (JSON) JSONObject.parse(msg);
            MpNotifyRecord notifyRecord = JSONObject.toJavaObject(json, MpNotifyRecord.class);
            if (Objects.isNull(notifyRecord)) {
                LOG.info("Convert notify record is null!");
                return;
            }
            LOG.info("Appending status to notify record...");
            notifyRecord.setStatus(NotifyStatusEnum.CREATED.name());
            notifyRecord.setCreateTime(new Date());
            notifyRecord.setLastNotifyTime(new Date());

            if (StringUtils.hasText(String.valueOf(notifyRecord.getId()))) {
                MpNotifyRecord notifyRecordRet =
                        mpNotifyRecordService.getNotifyRecordById(notifyRecord.getId());
                if (Objects.nonNull(notifyRecordRet)) {
                    LOG.info("Notify record duplicated, return!");
                    return;
                }
            }

            try {
                notifyPersist.saveNotifyRecord(notifyRecord);
                notifyRecord = mpNotifyRecordService
                        .getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(
                                notifyRecord.getMerchantNo(),
                                notifyRecord.getMerchantOrderNo(),
                                notifyRecord.getNotifyType());
                notifyQueue.addItemToList(notifyRecord);
            } catch (BizException exp) {
                LOG.error("BizException ", exp);
            } catch (Exception e) {
                LOG.error("Exp ", e);
            }
        } catch (Exception e) {
            LOG.error("Got exception during message handling ", e);
        }
    }
}
