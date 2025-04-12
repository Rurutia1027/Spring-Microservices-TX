package com.mini.payment.config;

import com.mini.payment.app.notify.entity.NotifyStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotifyConfig {
    @Bean(name = "threadPool")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setKeepAliveSeconds(10000);
        threadPoolTaskExecutor.setMaxPoolSize(5);
        threadPoolTaskExecutor.setQueueCapacity(50);
        return threadPoolTaskExecutor;
    }

    @Bean(name = "notifyStrategy")
    public NotifyStrategy notifyStrategy() {
        NotifyStrategy notifyStrategy = new NotifyStrategy();
        Map<Integer, Integer> notifyParams = new HashMap<>();
        notifyParams.put(1, 0);
        notifyParams.put(2, 1);
        notifyParams.put(3, 2);
        notifyParams.put(4, 5);
        notifyParams.put(5, 15);
        notifyStrategy.setNotifyParams(notifyParams);
        notifyStrategy.setSuccessValue("success");
        return notifyStrategy;
    }
}
