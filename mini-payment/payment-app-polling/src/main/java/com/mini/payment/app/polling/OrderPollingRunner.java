package com.mini.payment.app.polling;

import com.mini.payment.app.polling.core.PollingPersist;
import com.mini.payment.app.polling.core.PollingTask;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class OrderPollingRunner {
    private static final Logger LOG = LoggerFactory.getLogger(OrderPollingRunner.class);

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
        startHandleTasks();
    }

    private void startHandleTasks() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            LOG.info("ThreadPool#ActiveThreadCount {}", cacheThreadPool.getActiveCount());
            LOG.info("ThreadPool#MaxThreadSize {}", cacheThreadPool.getMaxPoolSize());

            if (!tasks.isEmpty() && cacheThreadPool.getActiveCount() < cacheThreadPool.getMaxPoolSize()) {
                PollingTask task = tasks.poll();
                if (task != null) {
                    cacheThreadPool.execute(task);
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }
}
