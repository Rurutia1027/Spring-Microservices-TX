package com.cloud.payment.web.service.impl;

import com.cloud.payment.service.message.grpc.RpcRpTransactionMessageServiceGrpc;
import com.cloud.payment.service.message.grpc.SaveMessageRequest;
import com.cloud.payment.service.message.grpc.SaveMessageResponse;
import com.cloud.payment.web.dto.MessageIdRequestDto;
import com.cloud.payment.web.dto.MessageResponseDto;
import com.cloud.payment.web.dto.QueueNameRequestDto;
import com.cloud.payment.web.dto.SaveMessageRequestDto;
import com.cloud.payment.web.dto.SaveMessageResponseDto;
import com.cloud.payment.web.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final RpcRpTransactionMessageServiceGrpc.RpcRpTransactionMessageServiceBlockingStub serviceBlockingStub;

    @Override
    public SaveMessageResponseDto saveMessageWaitingConfirm(SaveMessageRequestDto request) {
        SaveMessageRequest saveMessageRequest = request.convertToSaveMessageRequest();
        SaveMessageResponse saveMessageResponse =
                serviceBlockingStub.saveMessageWaitingConfirm(saveMessageRequest);
        SaveMessageResponseDto responseDto = new SaveMessageResponseDto();
        return responseDto.convertToSaveMessageResponse(saveMessageResponse);
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
