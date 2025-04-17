package com.mini.payment.domain.persistence;

import com.mini.payment.MpNotifyApplicationTest;
import com.mini.payment.domain.notify.entity.MpNotifyRecord;
import com.mini.payment.domain.notify.enums.NotifyTypeEnum;
import com.mini.payment.utils.MockDataRecordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MpNotifyApplicationTest.class)
public class PersistenceServiceImplTest {
    @Autowired
    private PersistenceServiceImpl persistenceService;



    @Test
    public void initTest() {
        Assertions.assertNotNull(persistenceService);
        Assertions.assertNotNull(persistenceService.getSessionFactory());
    }

    @Test
    public void testSaveAllAndQuery() {
        List<MpNotifyRecord> mpNotifyRecords = MockDataRecordUtils.mockNotifyRecords(10);
        List<MpNotifyRecord> mpNotifyRecordsRet = persistenceService.saveAll(mpNotifyRecords);
        Assertions.assertNotNull(mpNotifyRecordsRet);
        Assertions.assertTrue(mpNotifyRecordsRet.size() > 0 && mpNotifyRecordsRet.size() == 10);

        List<MpNotifyRecord> recordList =
                persistenceService.query("from " + MpNotifyRecord.class.getName());
        Assertions.assertTrue(recordList.size() == mpNotifyRecords.size());
    }

    @Test
    public void testSaveOrUpdate() {
        // suppose we already have a record, and then save this record to db
        // and then query this record from db, and modify parts of the records in it
        // and then invoke saveOrUpdate
        // what we expected is: this record's id will not be changed
        // only updated is the updated field, other values remains the same
        MpNotifyRecord record = MockDataRecordUtils.mockNotifyRecord();
        record.setNotifyType(NotifyTypeEnum.WEPAY_SEARCH.name());

        MpNotifyRecord recordRet = persistenceService.save(record);
        Long idValue = recordRet.getId();
        Assertions.assertTrue(recordRet.getId() > 0);

        recordRet.setNotifyType(NotifyTypeEnum.MERCHANT.name());
        // true --> update based on previous record in db table
        // false --> create a new record and this will generate a new id
        MpNotifyRecord queryRet = persistenceService.save(recordRet, true);
        Assertions.assertTrue(queryRet.getId() > 0
                && queryRet.getId().equals(recordRet.getId()));
        Assertions.assertTrue(queryRet.getNotifyType().equals(NotifyTypeEnum.MERCHANT.name()));


        // well this time, we use the false -> this gonna create a new record
        // based on the previous record values
        recordRet.setId(null);
        MpNotifyRecord queryRet2 = persistenceService.save(recordRet, false);
        Assertions.assertTrue(idValue != queryRet2.getId());
        Assertions.assertTrue(queryRet2.getNotifyType().equals(NotifyTypeEnum.MERCHANT.name()));
    }
}