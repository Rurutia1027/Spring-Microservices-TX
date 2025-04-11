package com.mini.payment.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mini.payment.admin")
public class PaymentAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentAdminApplication.class, args);
    }
}