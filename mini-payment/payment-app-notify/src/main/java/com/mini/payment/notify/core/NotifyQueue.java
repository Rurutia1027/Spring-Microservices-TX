package com.mini.payment.notify.core;

import com.mini.payment.domains.notify.entity.MpNotifyRecord;
import com.mini.payment.notify.entity.NotifyStrategy;
import com.mini.payment.domains.notify.enums.NotifyStatusEnum;
import com.mini.payment.runner.NotifyAppInitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
public class NotifyQueue implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger LOG = LoggerFactory.getLogger(NotifyQueue.class);

    @Autowired
    private NotifyStrategy notifyStrategy;

    @Autowired
    private NotifyPersist notifyPersist;

    public void addItemToList(MpNotifyRecord record) {
        if (Objects.isNull(record)) {
            LOG.info("receive null record, return!");
            return;
        }
        Integer notifyTimes = record.getNotifyTimes();
        Integer notifyMaxTimeLimitation = 0;
        notifyMaxTimeLimitation = notifyStrategy.getMaxNotifyTimeLimitation();
        if (record.getVersion().intValue() == 0) {
            record.setLastNotifyTime(new Date());
        }
        long time = record.getLastNotifyTime().getTime();
        Map<Integer, Integer> notifyTimeTable = notifyStrategy.getNotifyParams();
        if (notifyTimes < notifyMaxTimeLimitation) {
            // this record's notify times not attach to max limitation threshold
            Integer nextKey = notifyTimes + 1;
            Integer next = notifyTimeTable.get(nextKey);
            if (Objects.nonNull(next)) {
                time += 1000 * 60 * next + 1;
                record.setLastNotifyTime(new Date(time));
                NotifyAppInitRunner.tasks.put(new NotifyTask(record, this, notifyPersist,
                        notifyStrategy));
            }
        } else {
            // this record's notify times already attach to max limitation times
            // just update it to db table with `FAILED` status
            try {
                MpNotifyRecord recordRet =
                        notifyPersist.updateNotifyRecord(record.getId(),
                                record.getNotifyTimes(), NotifyStatusEnum.FAILED.name());
                LOG.info("Failed send notify record with id {} updated to db table " +
                        "successfully!", recordRet.getId());
            } catch (Exception e) {
                LOG.error("Failed to update failed send notify record with id to db " +
                        "table", e);
            }
        }
    }

}
