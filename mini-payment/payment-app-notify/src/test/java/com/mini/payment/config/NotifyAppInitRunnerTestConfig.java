package com.mini.payment.config;

import com.mini.payment.notify.core.NotifyPersist;
import com.mini.payment.notify.core.NotifyQueue;
import com.mini.payment.domain.notify.service.MpNotifyRecordService;
import com.mini.payment.runner.NotifyAppInitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@TestConfiguration
public class NotifyAppInitRunnerTestConfig {
    @Bean("notifyAppInitRunnerTest")
    public NotifyAppInitRunner notifyAppInitRunner(
            @Qualifier("mpNotifyRecordService") MpNotifyRecordService mpNotifyRecordService,
            @Qualifier("notifyPersist") NotifyPersist notifyPersist,
            @Qualifier("notifyQueue") NotifyQueue notifyQueue,
            @Qualifier("threadPool") ThreadPoolTaskExecutor threadPool) {
        NotifyAppInitRunner runner = new NotifyAppInitRunner();

        runner.setMpNotifyRecordService(mpNotifyRecordService);
        runner.setNotifyPersist(notifyPersist);
        runner.setNotifyQueue(notifyQueue);
        runner.setThreadPool(threadPool);

        return runner;
    }
}
