package com.cloud.payment.message.service.consumer.service;

import com.cloud.payment.service.message.api.RpcRpTransactionMessageService;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @DubboReference
    private RpcRpTransactionMessageService rpcRpTransactionMessageService;

    public RpTransactionMessage getMessageById(String messageId) {
        return rpcRpTransactionMessageService.getMessageByMessageId(messageId);
    }
}
