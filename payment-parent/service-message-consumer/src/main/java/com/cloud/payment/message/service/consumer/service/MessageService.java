package com.cloud.payment.message.service.consumer.service;

import com.cloud.payment.service.message.api.RpTransactionMessageService;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @DubboReference
    private RpTransactionMessageService rpTransactionMessageService;

    public RpTransactionMessage getMessageById(String messageId) {
        return rpTransactionMessageService.getMessageByMessageId(messageId);
    }
}
