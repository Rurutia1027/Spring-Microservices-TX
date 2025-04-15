package com.mini.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mini.payment")
public class MpAppOrderPollingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MpAppOrderPollingApplication.class, args);
    }
}