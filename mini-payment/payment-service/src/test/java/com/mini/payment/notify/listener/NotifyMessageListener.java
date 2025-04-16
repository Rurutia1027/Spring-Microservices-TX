package com.mini.payment.notify.listener;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mini.payment.notify.handler.NotifyMessageHandler;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotifyMessageListener {
    private final NotifyMessageHandler notifyMessageHandler;

    @Value("${mq.queue-name.merchant-notify}")
    private String merchantNotifyQueue;

    @Value("${mq.queue-name.order-notify}")
    private String orderNotifyQueue;

    public NotifyMessageListener(NotifyMessageHandler notifyMessageHandler) {
        this.notifyMessageHandler = notifyMessageHandler;
    }

    @JmsListener(destination = "${mq.queue-name.merchant-notify}")
    public void receiveMerchantNotify(Message msg) {
        try {
            if (msg instanceof TextMessage textMessage) {
                JSONObject ret = JSON.parseObject(textMessage.getText(),
                        JSONObject.class);
                notifyMessageHandler.addToCache(merchantNotifyQueue, ret);
            }
        } catch (Exception e) {
            log.error("Failed to handle message ", e);
        }
    }

    @JmsListener(destination = "${mq.queue-name.order-notify}")
    public void receiveOrderNotify(Message msg) {
        try {
            if (msg instanceof TextMessage textMessage) {
                String orderNo = textMessage.getText();
                JSONObject ret = new JSONObject();
                ret.put("orderNo", orderNo);
                notifyMessageHandler.addToCache(orderNotifyQueue, ret);
            }

        } catch (JMSException e) {
            log.error("Failed to handle message ", e);
        }
    }
}
