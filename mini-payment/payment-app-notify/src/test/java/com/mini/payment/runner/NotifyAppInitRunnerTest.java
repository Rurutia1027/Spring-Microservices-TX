package com.mini.payment.runner;

import com.mini.payment.MpAppNotifyTestApplication;
import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.enums.NotifyStatusEnum;
import com.mini.payment.mock.MockDataRecordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = MpAppNotifyTestApplication.class)
public class NotifyAppInitRunnerTest {
    @Autowired
    @Qualifier("notifyAppInitRunnerTest")
    private NotifyAppInitRunner notifyAppInitRunner;

    @Test
    public void initTest() {
        Assertions.assertNotNull(notifyAppInitRunner);
        Assertions.assertNotNull(notifyAppInitRunner.getMpNotifyRecordService());
        Assertions.assertNotNull(notifyAppInitRunner.getThreadPool());
        Assertions.assertNotNull(notifyAppInitRunner.getNotifyPersist());
        Assertions.assertNotNull(notifyAppInitRunner.getNotifyQueue());
    }

    @Test
    @Transactional
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
}
