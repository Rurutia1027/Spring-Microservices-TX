package com.mini.payment.notify.service;

import com.mini.payment.notify.entity.MpNotifyRecord;
import com.mini.payment.notify.entity.MpNotifyRecordAuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MpNotifyRecordService {
    void notifySend(String notifyUrl, String merchantOrderNo, String merchantNo);

    void orderSend(String bankOrderNo);

    MpNotifyRecord getNotifyRecordById(String id);

    MpNotifyRecord getNotifyByMerchnatNoAndMerchantOrderNoAndNotifyType(
            String merchantNo, String merchantOrderNo, String notifyTyp);

    Page<MpNotifyRecord> listPage(MpNotifyRecord param, Pageable pageable);

    long createNotifyRecord(MpNotifyRecord mpNotifyRecord);

    MpNotifyRecord updateNotifyRecord(MpNotifyRecord mpNotifyRecord);

    MpNotifyRecordAuditLog createNotifyRecordAuditLog(MpNotifyRecordAuditLog auditLog);
}
