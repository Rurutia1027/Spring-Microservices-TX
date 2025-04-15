package com.mini.payment.config;

import jakarta.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMqListenerConfig {
    @Value("${mq.queue-name.trade-notify}")
    private String tradeNotifyQueueName;

    @Bean(name = "tradeNotifyQueue")
    public Queue tradeNotifyQueue() {
        return new ActiveMQQueue(tradeNotifyQueueName);
    }

    // queue listener container
//    @Bean(name = "tradeQueueListenerContainer")
//    public DefaultMessageListenerContainer tradeQueueListenerContainer(
//            @Qualifier("connectionFactory") ConnectionFactory connectionFactory,
//            @Qualifier("tradeNotifyQueue") Queue tradeNotifyQueue,
//            @Qualifier("consumerMessageListener") ConsumerMessageListener consumerMessageListener) {
//        DefaultMessageListenerContainer listenerContainer =
//                new DefaultMessageListenerContainer();
//        listenerContainer.setConnectionFactory(connectionFactory);
//        listenerContainer.setDestination(tradeNotifyQueue);
//        listenerContainer.setMessageListener(consumerMessageListener);
//        return listenerContainer;
//    }
}
