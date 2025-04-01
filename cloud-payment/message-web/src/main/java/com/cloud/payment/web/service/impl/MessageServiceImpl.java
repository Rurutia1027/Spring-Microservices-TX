package com.cloud.payment.web.service.impl;

import com.cloud.payment.web.dto.MessageIdRequestDto;
import com.cloud.payment.web.dto.MessageResponseDto;
import com.cloud.payment.web.dto.QueueNameRequestDto;
import com.cloud.payment.web.dto.SaveMessageRequestDto;
import com.cloud.payment.web.dto.SaveMessageResponseDto;
import com.cloud.payment.web.service.MessageService;

public class MessageServiceImpl implements MessageService {
    @Override
    public SaveMessageResponseDto saveMessageWaitingConfirm(SaveMessageRequestDto request) {
        return null;
    }

    @Override
    public void confirmAndSendMessage(MessageIdRequestDto request) {

    }

    @Override
    public SaveMessageResponseDto saveAndSendMessage(SaveMessageRequestDto request) {
        return null;
    }

    @Override
    public void directSendMessage(SaveMessageRequestDto request) {

    }

    @Override
    public void reSendMessage(SaveMessageRequestDto request) {

    }

    @Override
    public void reSendMessageByMessageId(MessageIdRequestDto request) {

    }

    @Override
    public void setMessageToAlreadyDead(MessageIdRequestDto request) {

    }

    @Override
    public MessageResponseDto getMessageByMessageId(MessageIdRequestDto request) {
        return null;
    }

    @Override
    public void deleteMessageByMessageId(MessageIdRequestDto request) {

    }

    @Override
    public void reSendAllDeadMessageByQueueName(QueueNameRequestDto request) {

    }
}
