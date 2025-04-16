package com.mini.payment.config;

import com.mini.payment.domain.notify.handler.NotifyMessageHandler;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@EnableJms
@TestConfiguration
public class JmsTemplateTestConfig {
    @Value("${mq.broker-url}")
    private String brokerUrl;

    @Value("${mq.username}")
    private String username;

    @Value("${mq.password}")
    private String password;

    @Bean
    public ConnectionFactory testConnectionFactory() {
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(testConnectionFactory());
        factory.setConcurrency("101");
        return factory;
    }

    @Bean
    public NotifyMessageHandler notifyMessageHandler() {
        return new NotifyMessageHandler();
    }
}
