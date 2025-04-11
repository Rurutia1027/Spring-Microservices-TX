package com.mini.payment.notify.service.impl;

import com.mini.payment.notify.repository.MpNotifyRecordAuditLogRepository;
import com.mini.payment.notify.service.MpNotifyRecordAuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mpNotifyRecordAuditLogService")
public class MpNotifyRecordAuditLogServiceImpl implements MpNotifyRecordAuditLogService {
    @Autowired
    private MpNotifyRecordAuditLogRepository mpNotifyRecordAuditLogRepository;


}
