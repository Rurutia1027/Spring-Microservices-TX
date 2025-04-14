package com.mini.payment.config;

import com.mini.payment.app.notify.core.NotifyPersist;
import com.mini.payment.app.notify.core.NotifyQueue;
import com.mini.payment.app.notify.service.MpNotifyRecordService;
import com.mini.payment.runner.NotifyAppInitRunner;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@TestConfiguration
public class NotifyAppInitRunnerTestConfig {
    @Autowired
    private MpNotifyRecordService mpNotifyRecordService;

    @Autowired
    private NotifyPersist notifyPersist;

    @Autowired
    private NotifyQueue notifyQueue;

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Value("${mq.queue-name.trade-notify}")
    private String tradeQueue;

    @Value("${mq.queue-name.order-notify}")
    private String orderQueue;

    @Value("${mq.queue-name.merchant-notify}")
    private String merchantQueue;

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

    // queues
    @Bean("merchantQueue")
    public Queue merchantNotifyQueue() {
        return new ActiveMQQueue(merchantQueue);
    }

    @Bean("tradeNotifyQueue")
    public Queue tradeNotifyQueue() {
        return new ActiveMQQueue(tradeQueue);
    }

    @Bean("orderNotifyQueue")
    public Queue orderNotifyQueue() {
        return new ActiveMQQueue(orderQueue);
    }

    // -- listeners --

    @Bean("notifyAppInitRunnerTest")
    public NotifyAppInitRunner notifyAppInitRunner() {
        NotifyAppInitRunner runner = new NotifyAppInitRunner();
        runner.setMpNotifyRecordService(mpNotifyRecordService);
        runner.setNotifyPersist(notifyPersist);
        runner.setNotifyQueue(notifyQueue);
        runner.setThreadPool(threadPool);

        return runner;
    }
//
//    @Bean("merchantNotifyListener")
//    public ConsumerMessageListener consumerMessageListener() {
//        return buildListener();
//    }
//
//
//    // Message Listener Containers
//    @Bean
//    public DefaultMessageListenerContainer merchantNotifyContainer(
//            ConnectionFactory connectionFactory,
//            @Qualifier("merchantNotifyQueue") Queue queue,
//            @Qualifier("merchantNotifyListener") ConsumerMessageListener listener) {
//        return buildContainer(connectionFactory, queue, listener);
//    }
//
//    @Bean
//    public DefaultMessageListenerContainer orderNotifyContainer(
//            ConnectionFactory connectionFactory,
//            @Qualifier("orderNotifyQueue") Queue queue,
//            @Qualifier("orderNotifyListener") ConsumerMessageListener listener) {
//        return buildContainer(connectionFactory, queue, listener);
//    }
//
//    @Bean
//    public DefaultMessageListenerContainer tradeNotifyContainer(
//            ConnectionFactory connectionFactory,
//            @Qualifier("tradeNotifyQueue") Queue queue,
//            @Qualifier("tradeNotifyListener") ConsumerMessageListener listener) {
//        return buildContainer(connectionFactory, queue, listener);
//    }
//
//    private ConsumerMessageListener buildListener() {
//        ConsumerMessageListener listener = new ConsumerMessageListener();
//        listener.setNotifyPersist(notifyPersist);
//        listener.setMpNotifyRecordService(mpNotifyRecordService);
//        listener.setNotifyPersist(notifyPersist);
//        return listener;
//    }
//
//    private DefaultMessageListenerContainer buildContainer(ConnectionFactory connectionFactory,
//                                                           Queue queue,
//                                                           ConsumerMessageListener listener) {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setDestination(queue);
//        container.setMessageListener(listener);
//        container.setConcurrency("1");
//        return container;
//    }
}
