package com.mini.payment.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsTemplateConfig {
    @Value("${mq.broker-url}")
    private String brokerUrl;

    @Value("${mq.username}")
    private String username;

    @Value("${mq.password}")
    private String password;

    @Value("${mq.queue-name.merchant-notify}")
    private String merchantNotifyQueue;

    @Value("${mq.queue-name.order-notify}")
    private String orderNotifyQueue;

    @Value("${mq.queue-name.trade-notify}")
    private String tradeNotifyQueue;

    @Value("${mq.pool.maxConnections:20}")
    private int maxConnections;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory(username, password, brokerUrl);
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setMaxConnections(maxConnections);
        return pooledConnectionFactory;
    }

    @Bean(name = "merchantNotifyJmsTemplate")
    public JmsTemplate notifyJmsTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory());
        template.setDefaultDestinationName(merchantNotifyQueue);
        return template;
    }

    @Bean(name = "orderNotifyJmsTemplate")
    public JmsTemplate orderJmsTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory());
        template.setDefaultDestinationName(orderNotifyQueue);
        return template;
    }

    @Bean(name = "tradeNotifyJmsTemplate")
    public JmsTemplate tradeNotifyJmsTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory());
        template.setDefaultDestinationName(tradeNotifyQueue);
        return template;
    }
}
