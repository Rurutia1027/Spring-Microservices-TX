package com.mini.payment.mock;

import com.mini.payment.domain.notify.entity.MpNotifyRecord;
import com.mini.payment.domain.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.domain.notify.enums.NotifyStatusEnum;
import com.mini.payment.domain.notify.enums.NotifyTypeEnum;
import com.mini.payment.utils.StringUtil;
import org.apache.hc.core5.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockDataRecordUtils {
    public static MpNotifyRecord mockNotifyRecord() {
        MpNotifyRecord mpNotifyRecord = new MpNotifyRecord();
        mpNotifyRecord.setCreateTime(new Date());
        mpNotifyRecord.setLastNotifyTime(new Date());
        mpNotifyRecord.setStatus(NotifyStatusEnum.CREATED.name());
        mpNotifyRecord.setNotifyType(NotifyTypeEnum.MERCHANT.name());
        mpNotifyRecord.setNotifyTimes(0);
        mpNotifyRecord.setUrl("http://localhost:8999/api/notify");
        mpNotifyRecord.setMerchantNo(StringUtil.get32UUID());
        mpNotifyRecord.setMerchantOrderNo(StringUtil.get36UUID());
        mpNotifyRecord.setLimitNotifyTimes(6);
        return mpNotifyRecord;
    }

    public static List<MpNotifyRecord> mockNotifyRecords(int len) {
        List<MpNotifyRecord> ret = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ret.add(mockNotifyRecord());
        }
        return ret;
    }

    public static MpNotifyRecordAuditLog mockNotifyAuditLog() {
        MpNotifyRecordAuditLog auditLog = new MpNotifyRecordAuditLog();
        auditLog.setEditTime(new Date());
        auditLog.setCreateTime(new Date());
        auditLog.setResponse(StringUtil.get32UUID());
        auditLog.setRequest(StringUtil.get32UUID());
        auditLog.setNotifyId(StringUtil.get32UUID());
        auditLog.setMerchantOrderNo(StringUtil.get36UUID());
        auditLog.setMerchantNo(StringUtil.get32UUID());
        auditLog.setHttpStatus(HttpStatus.SC_ACCEPTED);
        return auditLog;
    }

    public static List<MpNotifyRecordAuditLog> mockNotifyAuditLogs(int len) {
        List<MpNotifyRecordAuditLog> ret = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ret.add(mockNotifyAuditLog());
        }
        return ret;
    }

    public static MockMessage mockTradeMessage() {
        MockMessage mockMessage = new MockMessage();
        mockMessage.setMessageType("TRADE");
        mockMessage.setMsgUUID(StringUtil.get32UUID());
        mockMessage.setTimestamp(new Date().getTime());
        return mockMessage;
    }

    public static MockMessage mockOrderMessage() {
        MockMessage mockMessage = new MockMessage();
        mockMessage.setMessageType("ORDER");
        mockMessage.setMsgUUID(StringUtil.get32UUID());
        mockMessage.setTimestamp(new Date().getTime());
        return mockMessage;
    }

    public static MockMessage mockMerchantMessage() {
        MockMessage mockMessage = new MockMessage();
        mockMessage.setMessageType("MERCHANT");
        mockMessage.setMsgUUID(StringUtil.get32UUID());
        mockMessage.setTimestamp(new Date().getTime());
        return mockMessage;
    }
}
