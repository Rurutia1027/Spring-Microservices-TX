package com.cloud.payment.service.message;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDubbo
@SpringBootApplication
@EntityScan(basePackages = {"com.cloud.payment.common.core.domain", "com.cloud.payment.service.message.entity"})
@EnableJpaRepositories(basePackages = {"com.cloud.payment.common.core.persistence", "com" +
        ".cloud.payment.common.core.repository", "com.cloud.payment.service.message" +
        ".repository"})
public class ServiceMessageProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMessageProviderApp.class, args);
    }
}
