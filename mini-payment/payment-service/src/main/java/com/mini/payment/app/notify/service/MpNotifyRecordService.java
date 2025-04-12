package com.mini.payment.app.notify.service;

import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.entity.MpNotifyRecordAuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MpNotifyRecordService {
    void merchantNotifySend(String notifyUrl, String merchantNo, String merchantOrderNo);

    void orderNotifySend(String bankOrderNo);

    MpNotifyRecord getNotifyRecordById(String id);

    MpNotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(
            String merchantNo, String merchantOrderNo, String notifyTyp);

    Page<MpNotifyRecord> listPage(MpNotifyRecord param, Pageable pageable);

    MpNotifyRecord createNotifyRecord(MpNotifyRecord mpNotifyRecord);

    MpNotifyRecord updateNotifyRecord(MpNotifyRecord mpNotifyRecord);

    MpNotifyRecordAuditLog createNotifyRecordAuditLog(MpNotifyRecordAuditLog auditLog);
}
