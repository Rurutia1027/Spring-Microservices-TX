package com.cloud.payment.message.service.consumer.controller;


import com.cloud.payment.message.service.consumer.service.MessageQueueService;
import com.cloud.payment.message.service.consumer.service.RPCMessageService;
import com.cloud.payment.service.message.api.RpcRpTransactionMessageService;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HelloMessageController {

    @DubboReference(timeout = 60000)
    private RpcRpTransactionMessageService rpcRpTransactionMessageService;

    @Autowired
    @Qualifier("kafkaMQMessageQueueService")
    private MessageQueueService kafkaMQMessageQueueService;

    @Autowired
    @Qualifier("activeMQMessageQueueService")
    private MessageQueueService activeMQMessageQueueService;

    @Autowired
    private RPCMessageService RPCMessageService;

    @GetMapping("/test")
    public String getMessageHelloInfo() {
        String dest = UUID.randomUUID().toString();
        String messageBody = UUID.randomUUID().toString();
        return UUID.randomUUID().toString();
    }

    @GetMapping("/hello")
    public String getMessage() {
        String dest = UUID.randomUUID().toString();
        String messageBody = UUID.randomUUID().toString();

        kafkaMQMessageQueueService.sendMessage(dest, messageBody);
        activeMQMessageQueueService.sendMessage(dest, messageBody);
        return "Hello World";
    }

    @GetMapping("/hiMessage/{messageId}")
    public RpTransactionMessage getHiMessage(@PathVariable("messageId") String messageId) {
        RpTransactionMessage ret =
                rpcRpTransactionMessageService.getMessageByMessageId(messageId);
        return ret;
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<String> deleteHiMessage(@PathVariable("messageId") String messageId) {
        rpcRpTransactionMessageService.deleteMessageByMessageId(messageId);
        return ResponseEntity.ok("Message with ID " + messageId + " deleted successfully.");
    }

    @GetMapping("/helloMessage")
    public RpTransactionMessage getHelloMessage() {
        return RPCMessageService.getMessageById(UUID.randomUUID().toString());
    }
}
