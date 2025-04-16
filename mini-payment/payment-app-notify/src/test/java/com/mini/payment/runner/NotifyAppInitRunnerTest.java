package com.mini.payment.runner;

import com.mini.payment.MpAppNotifyTestApplication;
import com.mini.payment.notify.core.NotifyTask;
import com.mini.payment.domains.notify.entity.MpNotifyRecord;
import com.mini.payment.domains.notify.enums.NotifyStatusEnum;
import com.mini.payment.domains.notify.service.MpNotifyRecordService;
import com.mini.payment.mock.MockDataRecordUtils;
import com.mini.payment.utils.StringUtil;
import com.mini.payment.utils.httpclient.HttpParam;
import com.mini.payment.utils.httpclient.HttpResult;
import com.mini.payment.utils.httpclient.HttpUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.DelayQueue;

@SpringBootTest(classes = MpAppNotifyTestApplication.class)
public class NotifyAppInitRunnerTest {
    @Autowired
    private NotifyAppInitRunner notifyAppInitRunner;

    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Test
    public void initTest() {
        Assertions.assertNotNull(notifyAppInitRunner);
        Assertions.assertNotNull(mpNotifyRecordService);
        Assertions.assertNotNull(notifyAppInitRunner.getMpNotifyRecordService());
        Assertions.assertNotNull(notifyAppInitRunner.getThreadPool());
        Assertions.assertNotNull(notifyAppInitRunner.getNotifyPersist());
        Assertions.assertNotNull(notifyAppInitRunner.getNotifyQueue());
    }

    @Test
    public void testStartInitFromDB() {
        // mock dataset & insert dataset to db
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

        // save mock records to db
        for (MpNotifyRecord record : notifyRecords) {
            MpNotifyRecord ret = notifyAppInitRunner.getMpNotifyRecordService()
                    .createNotifyRecord(record);
            Assertions.assertTrue(ret.getId() > 0);
        }

        notifyAppInitRunner.startInitFromDB();
    }

    @Test
    public void testGetTasks() {
        // mock dataset & insert dataset to db
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

        // save mock records to db
        for (MpNotifyRecord record : notifyRecords) {
            MpNotifyRecord ret = notifyAppInitRunner.getMpNotifyRecordService()
                    .createNotifyRecord(record);
            Assertions.assertTrue(ret.getId() > 0);
        }

        notifyAppInitRunner.startInitFromDB();
        DelayQueue<NotifyTask> tasks = notifyAppInitRunner.getTasks();
        Assertions.assertTrue(Objects.nonNull(tasks) && tasks.size() > 0);
    }

    @Test
    public void testHttpUtil() {
        String url = "http://localhost:8910/api/notify/test";
        HttpParam param = new HttpParam(url);
        HttpResult httpResult = HttpUtils.httpRequest(param);
        Assertions.assertNotNull(httpResult);
    }

    @Test
    public void testStartThread() {
        // mock dataset & insert dataset to db
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

        // save mock records to db
        for (MpNotifyRecord record : notifyRecords) {
            MpNotifyRecord ret = notifyAppInitRunner.getMpNotifyRecordService()
                    .createNotifyRecord(record);
            Assertions.assertTrue(ret.getId() > 0);
        }

        notifyAppInitRunner.startInitFromDB();
        DelayQueue<NotifyTask> tasks = notifyAppInitRunner.getTasks();
        Assertions.assertTrue(Objects.nonNull(tasks) && tasks.size() > 0);
    }

    @Test
    public void testConsumeMessageFromActiveMQ() {
        MpNotifyRecord mpNotifyRecord = MockDataRecordUtils.mockNotifyRecord();
        Assertions.assertNotNull(mpNotifyRecord.getMerchantNo());
        mpNotifyRecord.setMerchantNo(StringUtil.get32UUID());
        mpNotifyRecordService.merchantNotifySend(mpNotifyRecord.getUrl(),
                mpNotifyRecord.getMerchantNo(), mpNotifyRecord.getMerchantOrderNo());
        DelayQueue<NotifyTask> tasks = notifyAppInitRunner.getTasks();
    }
}