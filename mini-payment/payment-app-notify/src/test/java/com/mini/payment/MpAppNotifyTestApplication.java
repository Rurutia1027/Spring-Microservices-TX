package com.mini.payment;

import com.mini.payment.config.ActiveMqListenerConfig;
import com.mini.payment.config.ConsumerMessageListenerTestConfig;
import com.mini.payment.config.NotifyAppInitRunnerTestConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.mini.payment")
public class MpAppNotifyTestApplication {
}
