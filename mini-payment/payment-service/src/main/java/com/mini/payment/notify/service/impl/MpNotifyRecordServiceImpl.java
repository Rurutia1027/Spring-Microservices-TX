package com.mini.payment.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mini.payment.notify.entity.MpNotifyRecord;
import com.mini.payment.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.notify.enums.NotifyStatusEnum;
import com.mini.payment.notify.enums.NotifyTypeEnum;
import com.mini.payment.notify.repository.MpNotifyRecordAuditLogRepository;
import com.mini.payment.notify.repository.MpNotifyRecordRespository;
import com.mini.payment.notify.service.MpNotifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("mpNotifyRecordService")
public class MpNotifyRecordServiceImpl implements MpNotifyRecordService {
    @Autowired
    @Qualifier("merchantNotifyJmsTemplate")
    private JmsTemplate merchantJmsTemplate;
    @Autowired
    @Qualifier("orderNotifyJmsTemplate")
    private JmsTemplate orderJmsTemplate;

    @Autowired
    private MpNotifyRecordRespository mpNotifyRecordRespository;

    @Autowired
    private MpNotifyRecordAuditLogRepository mpNotifyRecordAuditLogRepository;


    @Override
    public void merchantNotifySend(String notifyUrl, String merchantOrderNo,
                                   String merchantNo) {
        MpNotifyRecord mpNotifyRecord = new MpNotifyRecord();
        mpNotifyRecord.setNotifyTimes(0);
        mpNotifyRecord.setLimitNotifyTimes(5);
        mpNotifyRecord.setStatus(NotifyStatusEnum.CREATED.name());
        mpNotifyRecord.setUrl(notifyUrl);
        mpNotifyRecord.setMerchantNo(merchantNo);
        mpNotifyRecord.setMerchantOrderNo(merchantOrderNo);
        mpNotifyRecord.setNotifyType(NotifyTypeEnum.MERCHANT.name());

        Object toJson = JSONObject.toJSON(mpNotifyRecord);
        final String msgStr = toJson.toString();
        merchantJmsTemplate.send(session -> session.createTextMessage(msgStr));
    }

    @Override
    public void orderNotifySend(String bankOrderNo) {
        final String orderNo = bankOrderNo;
        orderJmsTemplate.send(session -> session.createTextMessage(orderNo));
    }

    @Override
    public MpNotifyRecord getNotifyRecordById(String id) {
        return mpNotifyRecordRespository
                .findById(Long.parseLong(id)).orElse(null);
    }

    @Override
    public MpNotifyRecord getNotifyByMerchnatNoAndMerchantOrderNoAndNotifyType(String merchantNo, String merchantOrderNo, String notifyTyp) {
        MpNotifyRecord ret = null;

        // todo: Use Specificaiton dynamic query to query db
        return ret;
    }

    @Override
    public Page<MpNotifyRecord> listPage(MpNotifyRecord param, Pageable pageable) {
        return null;
    }

    @Override
    public MpNotifyRecord createNotifyRecord(MpNotifyRecord mpNotifyRecord) {
        return mpNotifyRecordRespository.save(mpNotifyRecord);
    }

    @Override
    public MpNotifyRecord updateNotifyRecord(MpNotifyRecord mpNotifyRecord) {
        return mpNotifyRecordRespository.save(mpNotifyRecord);
    }

    @Override
    public MpNotifyRecordAuditLog createNotifyRecordAuditLog(MpNotifyRecordAuditLog auditLog) {
        return mpNotifyRecordAuditLogRepository.save(auditLog);
    }
}
