package com.cloud.payment.web.service;

import com.cloud.payment.web.dto.MessageIdRequestDto;
import com.cloud.payment.web.dto.MessageResponseDto;
import com.cloud.payment.web.dto.QueueNameRequestDto;
import com.cloud.payment.web.dto.SaveMessageRequestDto;
import com.cloud.payment.web.dto.SaveMessageResponseDto;

public interface MessageService {
    SaveMessageResponseDto saveMessageWaitingConfirm(SaveMessageRequestDto request);

    void confirmAndSendMessage(MessageIdRequestDto request);

    SaveMessageResponseDto saveAndSendMessage(SaveMessageRequestDto request);

    void directSendMessage(SaveMessageRequestDto request);

    void reSendMessage(SaveMessageRequestDto request);

    void reSendMessageByMessageId(MessageIdRequestDto request);

    void setMessageToAlreadyDead(MessageIdRequestDto request);

    MessageResponseDto getMessageByMessageId(MessageIdRequestDto request);

    void deleteMessageByMessageId(MessageIdRequestDto request);

    void reSendAllDeadMessageByQueueName(QueueNameRequestDto request);
}
