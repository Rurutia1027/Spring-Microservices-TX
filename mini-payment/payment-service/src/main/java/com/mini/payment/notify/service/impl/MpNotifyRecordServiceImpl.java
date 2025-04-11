package com.mini.payment.notify.service.impl;

import com.mini.payment.notify.entity.MpNotifyRecord;
import com.mini.payment.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.notify.repository.MpNotifyRecordAuditLogRepository;
import com.mini.payment.notify.repository.MpNotifyRecordRespository;
import com.mini.payment.notify.service.MpNotifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("mpNotifyRecordService")
public class MpNotifyRecordServiceImpl implements MpNotifyRecordService {
//    @Autowired
//    private JmsTemplate notifyJmsTemplate;

    @Autowired
    private MpNotifyRecordRespository mpNotifyRecordRespository;

    @Autowired
    private MpNotifyRecordAuditLogRepository mpNotifyRecordAuditLogRepository;

    @Override
    public void notifySend(String notifyUrl, String merchantOrderNo, String merchantNo) {

    }

    @Override
    public void orderSend(String bankOrderNo) {

    }

    @Override
    public MpNotifyRecord getNotifyRecordById(String id) {
        return null;
    }

    @Override
    public MpNotifyRecord getNotifyByMerchnatNoAndMerchantOrderNoAndNotifyType(String merchantNo, String merchantOrderNo, String notifyTyp) {
        return null;
    }

    @Override
    public Page<MpNotifyRecord> listPage(MpNotifyRecord param, Pageable pageable) {
        return null;
    }

    @Override
    public long createNotifyRecord(MpNotifyRecord mpNotifyRecord) {
        return 0;
    }

    @Override
    public MpNotifyRecord updateNotifyRecord(MpNotifyRecord mpNotifyRecord) {
        return null;
    }

    @Override
    public MpNotifyRecordAuditLog createNotifyRecordAuditLog(MpNotifyRecordAuditLog auditLog) {
        return null;
    }
}
