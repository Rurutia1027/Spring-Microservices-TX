package com.mini.payment.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsTemplateConfig {

    @Value("${mq.queue-name.merchant-notify}")
    private String merchantNotifyQueue;

    @Value("${mq.queue-name.order-notify}")
    private String orderNotifyQueue;

    @Value("${mq.queue-name.trade-notify}")
    private String tradeNotifyQueue;

    @Bean(name = "merchantNotifyJmsTemplate")
    public JmsTemplate notifyJmsTemplate(
            @Qualifier("connectionFactory") SingleConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setDefaultDestinationName(merchantNotifyQueue);
        return template;
    }

    @Bean(name = "orderNotifyJmsTemplate")
    public JmsTemplate orderJmsTemplate(
            @Qualifier("connectionFactory") SingleConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setDefaultDestinationName(orderNotifyQueue);
        return template;
    }

    @Bean(name = "tradeNotifyJmsTemplate")
    public JmsTemplate tradeNotifyJmsTemplate(
            @Qualifier("connectionFactory") SingleConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setDefaultDestinationName(tradeNotifyQueue);
        return template;
    }
}
