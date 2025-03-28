package com.cloud.payment.service.message.service.impl;

import com.cloud.payment.service.message.service.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Deprecated
@Service("kafkaMQMessageQueueService")
public class KafkaMessageQueueServiceImpl implements MessageQueueService {


    @Autowired
    private JmsTemplate jmsTemplate; ;

    @Override
    public void sendMessage(String destination, String message) {
        System.out.println(Objects.isNull(jmsTemplate));
        System.out.println("send message to destination " + destination + " " + message);
    }


    @Override
    public String receiveMessage(String destination) {
        return "";
    }

    @Override
    public void setupProducer(String destination) {

    }

    @Override
    public void setupConsumer(String destination) {

    }

    @Override
    public void close() {

    }
}
