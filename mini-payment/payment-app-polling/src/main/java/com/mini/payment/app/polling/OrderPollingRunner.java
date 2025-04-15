package com.mini.payment.app.polling;

import com.mini.payment.app.polling.core.PollingPersist;
import com.mini.payment.app.polling.core.PollingTask;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;

@Component
public class OrderPollingRunner {

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Autowired
    private PollingPersist pollingPersist;

    public static ThreadPoolTaskExecutor cacheThreadPool;

    public static PollingPersist cachePollingPersist;

    public static DelayQueue<PollingTask> tasks = new DelayQueue<>();

    @PostConstruct
    public void init() {
        cachePollingPersist = pollingPersist;
        cacheThreadPool = threadPool;

        startThread();
    }

    private void startThread() {
    }
}
