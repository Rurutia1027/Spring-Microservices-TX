package com.mini.payment.domain.notify.repository;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domain.notify.entity.MpNotifyRecord;
import com.mini.payment.utils.MockDataRecordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class MpNotifyRecordRepositoryTest {
    @Autowired
    private MpNotifyRecordRepository mpNotifyRecordRepository;

    @Test
    public void initTest() {
        Assertions.assertNotNull(mpNotifyRecordRepository);
    }

    @Test
    public void saveAndQuery() {
        MpNotifyRecord record = MockDataRecordUtils.mockNotifyRecord();
        MpNotifyRecord recordRet = mpNotifyRecordRepository.save(record);
        Assertions.assertNotNull(record);
        Assertions.assertTrue(recordRet.getId() > 0);
    }

    @Test
    public void saveAndDelete() {
        MpNotifyRecord record = MockDataRecordUtils.mockNotifyRecord();
        MpNotifyRecord recordRet = mpNotifyRecordRepository.save(record);
        Assertions.assertTrue(record.getId() > 0);
        mpNotifyRecordRepository.delete(recordRet);
        Assertions.assertTrue(mpNotifyRecordRepository.findById(record.getId()).isEmpty());
    }
}