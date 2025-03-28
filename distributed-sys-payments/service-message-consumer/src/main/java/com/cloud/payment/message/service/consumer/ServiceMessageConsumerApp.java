package com.cloud.payment.message.service.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ServiceMessageConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMessageConsumerApp.class, args);
    }
}