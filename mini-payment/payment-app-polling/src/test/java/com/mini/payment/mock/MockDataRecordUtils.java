package com.mini.payment.mock;

import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.app.notify.enums.NotifyStatusEnum;
import com.mini.payment.app.notify.enums.NotifyTypeEnum;
import com.mini.payment.trade.entity.Payment;
import com.mini.payment.utils.StringUtil;
import org.apache.hc.core5.http.HttpStatus;

import java.math.BigDecimal;
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

    public static List<Payment> mockPayment(int len) {
        List<Payment> payments = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            payments.add(mockPayment());
        }

        return payments;
    }

    private static Payment mockPayment() {
        Payment ret = new Payment();
        ret.setBankOrderNo(StringUtil.get32UUID());
        ret.setOrderAmount(BigDecimal.TEN);
        ret.setOrderNo(StringUtil.get32UUID());
        ret.setCreateTime(new Date());
        ret.setEditTime(new Date());
        ret.setRefundTimes(29);
        ret.setVersion(0);
        return ret;
    }
}
