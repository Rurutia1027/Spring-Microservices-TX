package com.cloud.payment.service.message.service.impl;

import com.cloud.payment.service.message.service.MessageQueueService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("activeMQMessageQueueService")
public class ActiveMQMessageQueueServiceImpl implements MessageQueueService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String destination, String message) {
        System.out.println(Objects.isNull(jmsTemplate));
        System.out.println("send message to destination " + destination + " " + message);
        if (StringUtils.isEmpty(destination) || StringUtils.isEmpty(message)) {
            // todo: okkkk, i know System out is not formal, will modify this together via
            //  log4j after
            //  we implement message queue communication.
            System.out.println("received invalid destination or message here , abort message" +
                    " deliver!");
        }

        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
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
