package com.mini.payment.app.notify.core;

import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("notifyPersist")
public class NotifyPersist {
    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    public MpNotifyRecord saveNotifyRecord(MpNotifyRecord record) {
        return mpNotifyRecordService.createNotifyRecord(record);
    }

    // update merchant notify record's record
    public MpNotifyRecord updateNotifyRecord(Long id, int notifyTimes, String status) {
        MpNotifyRecord notifyRecord = mpNotifyRecordService.getNotifyRecordById(id);
        notifyRecord.setNotifyTimes(notifyTimes);
        notifyRecord.setStatus(status);
        notifyRecord.setLastNotifyTime(new Date());
        return mpNotifyRecordService.updateNotifyRecord(notifyRecord);
    }

    // create merchant notify record's audit log
    public MpNotifyRecordAuditLog saveNotifyRecordAuditLog(String notifyId,
                                                           String merchantNo, String merchantOrderNo,
                                                           String request, String response,
                                                           int httpStatus) {
        MpNotifyRecordAuditLog notifyRecordAuditLog = new MpNotifyRecordAuditLog();
        notifyRecordAuditLog.setHttpStatus(httpStatus);
        notifyRecordAuditLog.setMerchantNo(merchantNo);
        notifyRecordAuditLog.setMerchantOrderNo(merchantOrderNo);
        notifyRecordAuditLog.setNotifyId(notifyId);
        notifyRecordAuditLog.setRequest(request);
        notifyRecordAuditLog.setResponse(response);
        notifyRecordAuditLog.setCreateTime(new Date());
        notifyRecordAuditLog.setEditTime(new Date());
        return mpNotifyRecordService.createNotifyRecordAuditLog(notifyRecordAuditLog);
    }
}
