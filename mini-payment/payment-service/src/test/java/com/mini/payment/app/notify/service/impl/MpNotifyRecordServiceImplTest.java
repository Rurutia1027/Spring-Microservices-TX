package com.mini.payment.app.notify.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.app.notify.handler.NotifyMessageHandler;
import com.mini.payment.app.notify.listener.NotifyMessageListener;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.utils.MockDataRecordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class MpNotifyRecordServiceImplTest {
    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Autowired
    private NotifyMessageListener notifyMessageListener;

    @Autowired
    private NotifyMessageHandler notifyMessageHandler;

    @Value("${mq.queue-name.merchant-notify}")
    private String merchantNotifyQueue;

    @Value("${mq.queue-name.order-notify}")
    private String orderNotifyQueue;


    @Test
    public void initTest() {
        Assertions.assertNotNull(mpNotifyRecordService);
        Assertions.assertNotNull(notifyMessageListener);
    }

    @Test
    public void testCreateAndQueryMpNotifyRecord() {
        MpNotifyRecord record = MockDataRecordUtils.mockNotifyRecord();
        String merchantNo = record.getMerchantNo();
        String merchantOrderNo = record.getMerchantOrderNo();
        String notifyRecordType = record.getNotifyType();
        MpNotifyRecord recordRet = mpNotifyRecordService.createNotifyRecord(record);
        Assertions.assertTrue(recordRet.getId() > 0);
        MpNotifyRecord queryRet =
                mpNotifyRecordService.getNotifyByMerchantNoAndMerchantOrderNoAndNotifyType(
                        merchantNo, merchantOrderNo, notifyRecordType);
        Assertions.assertNotNull(queryRet);
        Assertions.assertEquals(merchantNo, queryRet.getMerchantNo());
        Assertions.assertEquals(merchantOrderNo, queryRet.getMerchantOrderNo());
        Assertions.assertEquals(notifyRecordType, queryRet.getNotifyType());
    }

    @Test
    public void testGetNotifyRecordById() {
        MpNotifyRecord record = MockDataRecordUtils.mockNotifyRecord();
        String merchantNo = record.getMerchantNo();
        String merchantOrderNo = record.getMerchantOrderNo();
        String notifyRecordType = record.getNotifyType();
        MpNotifyRecord recordRet = mpNotifyRecordService.createNotifyRecord(record);
        Assertions.assertTrue(recordRet.getId() > 0);

        MpNotifyRecord queryRet =
                mpNotifyRecordService.getNotifyRecordById(Long.toString(recordRet.getId()));
        Assertions.assertEquals(recordRet, queryRet);
    }

    @Test
    public void testCreateNotifyRecordAuditLog() {
        MpNotifyRecordAuditLog mpNotifyRecordAuditLog =
                MockDataRecordUtils.mockNotifyAuditLog();
        MpNotifyRecordAuditLog auditLogRet =
                mpNotifyRecordService.createNotifyRecordAuditLog(mpNotifyRecordAuditLog);
        Assertions.assertTrue(auditLogRet.getId() > 0);
    }

    @Test
    public void testOrderNotifySend() throws InterruptedException {
        MpNotifyRecord mpNotifyRecord = MockDataRecordUtils.mockNotifyRecord();
        Assertions.assertNotNull(mpNotifyRecord.getMerchantOrderNo());
        mpNotifyRecordService.orderNotifySend(mpNotifyRecord.getMerchantOrderNo());

        Thread.sleep(1000L);
        List<JSONObject> messageList = notifyMessageHandler.getFromCache(orderNotifyQueue);
        Assertions.assertTrue(messageList.size() > 0);
    }

    @Test
    public void testMerchantNotifySend() throws InterruptedException {
        MpNotifyRecord mpNotifyRecord = MockDataRecordUtils.mockNotifyRecord();
        Assertions.assertNotNull(mpNotifyRecord.getMerchantNo());
        mpNotifyRecordService.merchantNotifySend(mpNotifyRecord.getUrl(),
                mpNotifyRecord.getMerchantNo(), mpNotifyRecord.getMerchantOrderNo());
        Thread.sleep(1000L);
        List<JSONObject> messageList = notifyMessageHandler.getFromCache(merchantNotifyQueue);
        Assertions.assertTrue(messageList.size() > 0);
    }
}