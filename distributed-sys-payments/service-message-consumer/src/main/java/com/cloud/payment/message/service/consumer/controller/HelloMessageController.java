package com.cloud.payment.message.service.consumer.controller;


import com.cloud.payment.message.service.consumer.service.MessageService;
import com.cloud.payment.service.message.api.RpcRpTransactionMessageService;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HelloMessageController {

    @DubboReference
    private RpcRpTransactionMessageService rpcRpTransactionMessageService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/hello")
    public String getMessage() {
        return "Hello World";
    }

    @GetMapping("/hiMessage/{messageId}")
    public RpTransactionMessage getHiMessage(@PathVariable("messageId") String messageId) {
        RpTransactionMessage ret =
                rpcRpTransactionMessageService.getMessageByMessageId(messageId);
        return ret;
    }

    @GetMapping("/helloMessage")
    public RpTransactionMessage getHelloMessage() {
        return messageService.getMessageById(UUID.randomUUID().toString());
    }
}
