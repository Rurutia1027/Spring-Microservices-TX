package com.mini.payment.config;

import com.mini.payment.app.notify.message.ConsumerMessageListener;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class ActiveMqListenerConfig {
    @Value("${mq.queue-name.trade-notify}")
    private String tradeNotifyQueueName;

    @Bean(name = "tradeNotifyQueue")
    public Queue tradeNotifyQueue() {
        return new ActiveMQQueue(tradeNotifyQueueName);
    }

    // queue listener container
    @Bean(name = "tradeQueueListenerContainer")
    public DefaultMessageListenerContainer tradeQueueListenerContainer(
            @Qualifier("connectionFactory") ConnectionFactory connectionFactory,
            @Qualifier("tradeNotifyQueue") Queue tradeNotifyQueue,
            ConsumerMessageListener consumerMessageListener) {
        DefaultMessageListenerContainer listenerContainer =
                new DefaultMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setDestination(tradeNotifyQueue);
        listenerContainer.setMessageListener(consumerMessageListener);
        return listenerContainer;
    }
}
