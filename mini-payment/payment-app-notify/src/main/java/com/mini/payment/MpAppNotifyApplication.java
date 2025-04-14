package com.mini.payment;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.mini.payment")
public class MpAppNotifyApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(MpAppNotifyApplication.class)
                .run(args);
    }
}
