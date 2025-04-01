package com.cloud.payment.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Hooks;

@Slf4j
@Configuration
public class ObservabilityConfig {
    @EventListener(ApplicationStartedEvent.class)
    public void onStart() {
        Hooks.enableAutomaticContextPropagation();
    }
}
