package com.cloud.payment.web.controller;

import com.cloud.payment.web.dto.SaveMessageRequestDto;
import com.cloud.payment.web.dto.SaveMessageResponseDto;
import com.cloud.payment.web.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MessageServiceController {
    private final MessageService messageService;

    @Autowired
    public MessageServiceController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/test")
    public Mono<SaveMessageResponseDto> saveMessageWaitingConfirm() {
        String messageId = UUID.randomUUID().toString();
        SaveMessageRequestDto requestDto = new SaveMessageRequestDto();
        requestDto.setMessageId(messageId);
        requestDto.setMessageBody(UUID.randomUUID().toString());
        requestDto.setConsumerQueue(UUID.randomUUID().toString());
        SaveMessageResponseDto saveMessageResponseDto =
                messageService.saveMessageWaitingConfirm(requestDto);
        return Mono.just(saveMessageResponseDto);
    }
}
