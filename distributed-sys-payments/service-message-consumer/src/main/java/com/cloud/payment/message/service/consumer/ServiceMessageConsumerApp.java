package com.cloud.payment.message.service.consumer;

import com.cloud.payment.message.service.consumer.config.JmsConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableDubbo
@SpringBootApplication
 @EnableConfigurationProperties(JmsConfig.class)
public class ServiceMessageConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMessageConsumerApp.class, args);
    }
}