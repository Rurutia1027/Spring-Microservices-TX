package com.mini.payment.config;

import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.runner.NotifyAppInitRunner;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class NotifyAppInitRunnerConfig {
    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Autowired
    private NotifyPersist notifyPersist;

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Bean("notifyAppInitRunnerTest")
    @Qualifier("notifyAppInitRunnerTest")
    public NotifyAppInitRunner notifyAppInitRunner() {
        NotifyAppInitRunner runner = new NotifyAppInitRunner();
        runner.setMpNotifyRecordService(mpNotifyRecordService);
        runner.setNotifyPersist(notifyPersist);
        runner.setNotifyQueue(notifyQueue);
        runner.setThreadPool(threadPool);

        return runner;
    }
}
