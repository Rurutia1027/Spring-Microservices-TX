package com.mini.payment.app.notify.runner;

import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.core.NotifyTask;
import com.mini.payment.app.notify.entity.MpNotifyRecord;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.DelayQueue;

@Component
public class NotifyAppInitRunner {
    private static final Logger LOG = LoggerFactory.getLogger(NotifyAppInitRunner.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private NotifyPersist notifyPersist;

    private static DelayQueue<NotifyTask> tasks = new DelayQueue<>();

    @PostConstruct
    public void init() {
        LOG.info("NotifyInit Runner ");
        startInitFromDB();
        startThread();
    }

    // todo: replace this one: @EventListener(ApplicationReadyEvent.class) or @Scheduled
    // todo: try something modern of spring framework <(｀^´)>
    private void startThread() {
        threadPool.execute(() -> {
            try {
                while (true) {
                    Thread.sleep(50);
                    if (threadPool.getActiveCount() < threadPool.getMaxPoolSize()) {
                        final NotifyTask task = tasks.poll();
                        if (Objects.nonNull(task)) {
                            threadPool.execute(() -> {
                                tasks.remove(task);
                                task.run();
                            });
                        }
                    }
                }
            } catch (Exception e) {
                LOG.error("Internal error ", e);
            }
        });
    }

    // load notify data records from db to init
    // NotifyQueue's cache list
    private void startInitFromDB() {
        LOG.info("load notify records from db to task list");
        int pageNum = 0;
        int pageSize = 500;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Integer> queryRecordHttpStatusList = List.of(101, 102, 200, 201);
        List<Integer> queryRecordNotifyTimeList = List.of(0, 1, 2, 3, 4);

        MpNotifyRecord queryParam = new MpNotifyRecord();

        Page<MpNotifyRecord> notifyRecords = mpNotifyRecordService.loadRecords(queryRecordHttpStatusList,
                queryRecordNotifyTimeList, pageable);

        // here we process each notify record
        // update it's inner record values, convert notify record into NotifyTask,
        // and finally append the task to the task queue
        while (notifyRecords.hasContent()) {
            // handle records in batch and the batch size = pageSize
            for (MpNotifyRecord notifyRecord : notifyRecords.getContent()) {
                // update record's last notify timestamp value
                notifyRecord.setLastNotifyTime(new Date());
                notifyQueue.addItemToList(notifyRecord);
            }

            pageNum++;
            // continue fetch next page(batch) records from DB to cache/memory
            pageable = PageRequest.of(pageNum, pageSize);
            notifyRecords = mpNotifyRecordService.loadRecords(queryRecordHttpStatusList,
                    queryRecordNotifyTimeList, pageable);
        }
    }
}
