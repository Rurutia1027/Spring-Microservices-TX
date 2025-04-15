package com.mini.payment.config;

import com.mini.payment.app.polling.listener.PollingMessageListener;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class ActiveMqListenerConfig {
    @Value("${mq.queue-name.order-notify}")
    private String orderQueueName;

    @Bean(name = "orderQueue")
    public ActiveMQQueue orderQueue() {
        return new ActiveMQQueue(orderQueueName);
    }

    // listener container
    @Bean(name = "orderQueueMessageListenerContainer")
    public DefaultMessageListenerContainer orderQueueMessageListenerContainer(
            @Qualifier("connectionFactory") SingleConnectionFactory connectionFactory,
            @Qualifier("orderQueue") ActiveMQQueue activeMQQueue,
            @Qualifier("pollingMessageListener") PollingMessageListener messageListener) {
        DefaultMessageListenerContainer messageListenerContainer =
                new DefaultMessageListenerContainer();
        return messageListenerContainer;
    }
}
