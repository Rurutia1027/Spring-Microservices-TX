package com.cloud.payment.message.service.consumer.listener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueMessageListener {
    @Value("${spring.activemq.queue-name}")
    private String queueName;

    @JmsListener(destination = "${spring.activemq.queue-name}")
    public void receiveQueueMessage(String message) {
        System.out.println("Received message from queue ===> " + message);
    }
}
