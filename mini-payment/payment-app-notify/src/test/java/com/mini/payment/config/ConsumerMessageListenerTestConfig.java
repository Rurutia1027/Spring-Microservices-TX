package com.mini.payment.config;

import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.listeners.BaseMessageListener;
import com.mini.payment.listeners.MerchantQueueMessageListener;
import com.mini.payment.listeners.OrderQueueMessageListener;
import com.mini.payment.listeners.TradeQueueMessageListener;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@TestConfiguration
public class ConsumerMessageListenerTestConfig {
    @Value("${mq.broker-url}")
    private String brokerUrl;

    @Value("${mq.username}")
    private String username;

    @Value("${mq.password}")
    private String password;

    @Value("${mq.queue-name.trade-notify}")
    private String tradeNotifyQueueName;

    @Value("${mq.queue-name.merchant-notify}")
    private String merchantNotifyQueueName;

    @Value("${mq.queue-name.order-notify}")
    private String orderNotifyQueueName;

    @Bean("testConnectionFactory")
    public ConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory(username, password, brokerUrl);
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setMaxConnections(10);
        return pooledConnectionFactory;
    }

    // create queue instances for testing
    @Bean("tradeNotifyTestQueue")
    public Queue tradeNotifyQueue() {
        return new ActiveMQQueue(tradeNotifyQueueName);
    }

    @Bean("orderNotifyTestQueue")
    public Queue orderNotifyQueue() {
        return new ActiveMQQueue(orderNotifyQueueName);
    }

    @Bean("merchantTestQueue")
    public Queue merchantNotifyQueue() {
        return new ActiveMQQueue(merchantNotifyQueueName);
    }

    // -- create listeners ---
    @Bean("tradeNotifyQueueListener")
    public TradeQueueMessageListener tradeNotifyQueueListener(@Qualifier("notifyQueue") NotifyQueue notifyQueue,
                                                              @Qualifier("mpNotifyRecordService") MpNotifyRecordService mpNotifyRecordService,
                                                              @Qualifier("notifyPersist") NotifyPersist notifyPersist) {
        return new TradeQueueMessageListener(notifyQueue, mpNotifyRecordService,
                notifyPersist);
    }

    @Bean("orderNotifyQueueListener")
    public OrderQueueMessageListener orderNotifyQueueListener(@Qualifier("notifyQueue") NotifyQueue notifyQueue,
                                                              @Qualifier("mpNotifyRecordService") MpNotifyRecordService mpNotifyRecordService,
                                                              @Qualifier("notifyPersist") NotifyPersist notifyPersist) {
        return new OrderQueueMessageListener(notifyQueue, mpNotifyRecordService,
                notifyPersist);
    }

    @Bean("merchantNotifyQueueListener")
    public MerchantQueueMessageListener merchantNotifyQueueListener(@Qualifier("notifyQueue") NotifyQueue notifyQueue,
                                                                    @Qualifier("mpNotifyRecordService") MpNotifyRecordService mpNotifyRecordService,
                                                                    @Qualifier("notifyPersist") NotifyPersist notifyPersist) {
        return new MerchantQueueMessageListener(notifyQueue, mpNotifyRecordService,
                notifyPersist);
    }

    // -- listener container
    @Bean
    public DefaultMessageListenerContainer merchantNotifyListenerContainer(
            @Qualifier("testConnectionFactory") ConnectionFactory connectionFactory,
            @Qualifier("merchantNotifyQueue") Queue queue,
            @Qualifier("merchantNotifyListener") MerchantQueueMessageListener listener) {
        return buildContainer(connectionFactory, queue, listener);
    }

    @Bean
    public DefaultMessageListenerContainer orderNotifyListenerContainer(
            @Qualifier("testConnectionFactory") ConnectionFactory connectionFactory,
            @Qualifier("orderNotifyQueue") Queue queue,
            @Qualifier("orderNotifyListener") OrderQueueMessageListener listener) {
        return buildContainer(connectionFactory, queue, listener);
    }

    @Bean
    public DefaultMessageListenerContainer tradeNotifyListenerContainer(
            @Qualifier("testConnectionFactory") ConnectionFactory connectionFactory,
            @Qualifier("tradeNotifyQueue") Queue queue,
            @Qualifier("tradeNotifyListener") TradeQueueMessageListener listener) {
        return buildContainer(connectionFactory, queue, listener);
    }

    // -- listener container builder --
    private DefaultMessageListenerContainer buildContainer(
            ConnectionFactory connectionFactory, Queue queue, BaseMessageListener listener) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(listener);
        container.setDestination(queue);
        return container;
    }
}
