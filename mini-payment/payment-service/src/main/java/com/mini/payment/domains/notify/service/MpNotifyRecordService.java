package com.mini.payment.domains.notify.service;

import com.mini.payment.domains.notify.entity.MpNotifyRecord;
import com.mini.payment.domains.notify.entity.MpNotifyRecordAuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MpNotifyRecordService {
    void merchantNotifySend(String notifyUrl, String merchantNo, String merchantOrderNo);

    void orderNotifySend(String bankOrderNo);

    MpNotifyRecord getNotifyRecordById(Long id);

    MpNotifyRecord getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(
            String merchantNo, String merchantOrderNo, String notifyTyp);

    Page<MpNotifyRecord> listPage(MpNotifyRecord param, Pageable pageable);

    Page<MpNotifyRecord> loadNotifyRecordsFromDB(List<String> notifyStatusList, List<Integer> notifyTimeList,
                                                 Pageable pageable);

    MpNotifyRecord createNotifyRecord(MpNotifyRecord mpNotifyRecord);

    MpNotifyRecord updateNotifyRecord(MpNotifyRecord mpNotifyRecord);

    MpNotifyRecordAuditLog createNotifyRecordAuditLog(MpNotifyRecordAuditLog auditLog);
}
