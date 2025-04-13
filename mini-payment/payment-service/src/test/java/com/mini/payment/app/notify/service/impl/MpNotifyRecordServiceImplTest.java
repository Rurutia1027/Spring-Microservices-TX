package com.mini.payment.app.notify.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.entity.MpNotifyRecordAuditLog;
import com.mini.payment.app.notify.enums.NotifyStatusEnum;
import com.mini.payment.app.notify.handler.NotifyMessageHandler;
import com.mini.payment.app.notify.listener.NotifyMessageListener;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.utils.MockDataRecordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
                mpNotifyRecordService.getNotifyRecordById(recordRet.getId());
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

    @Test
    public void testLoadNotifyRecordsFromDB() {
        // first we mock 5 records and save them to db
        List<MpNotifyRecord> notifyRecords = MockDataRecordUtils.mockNotifyRecords(6);
        notifyRecords.get(0).setStatus(NotifyStatusEnum.CREATED.name());
        notifyRecords.get(0).setNotifyTimes(0);
        notifyRecords.get(1).setStatus(NotifyStatusEnum.SUCCESS.name());
        notifyRecords.get(2).setStatus(NotifyStatusEnum.FAILED.name());
        notifyRecords.get(2).setNotifyTimes(1);
        notifyRecords.get(3).setStatus(NotifyStatusEnum.HTTP_REQUEST_SUCCESS.name());
        notifyRecords.get(4).setStatus(NotifyStatusEnum.HTTP_REQUEST_FALIED.name());
        notifyRecords.get(5).setStatus(NotifyStatusEnum.CREATED.name());
        notifyRecords.get(5).setNotifyTimes(5);

        for (MpNotifyRecord r : notifyRecords) {
            MpNotifyRecord ret = mpNotifyRecordService.createNotifyRecord(r);
            Assertions.assertTrue(ret.getId() > 0);
        }

        // then create query conditions to fetch records from db table
        int pageOffset = 0;
        int pageSize = 20;
        List<Integer> notifyRecordsNotifyTimes = List.of(0, 1, 2);
        List<String> notifyRecordStatus = List.of(NotifyStatusEnum.CREATED.name(),
                NotifyStatusEnum.FAILED.name());
        Pageable pageable = PageRequest.of(pageOffset, pageSize);
        Page<MpNotifyRecord> records = mpNotifyRecordService.loadNotifyRecordsFromDB(notifyRecordStatus,
                notifyRecordsNotifyTimes, pageable);
        Assertions.assertNotNull(records);
        Assertions.assertTrue(records.getContent().size() > 0);
    }
}