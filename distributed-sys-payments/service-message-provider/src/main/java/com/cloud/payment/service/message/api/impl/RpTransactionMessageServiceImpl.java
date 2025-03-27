package com.cloud.payment.service.message.api.impl;

import com.cloud.payment.service.message.api.RpTransactionMessageService;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import com.cloud.payment.service.message.exception.MessageBizException;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.UUID;

@DubboService
public class RpTransactionMessageServiceImpl implements RpTransactionMessageService {
    @Override
    public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
        return 0;
    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MessageBizException {

    }

    @Override
    public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {
        return 0;
    }

    @Override
    public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {

    }

    @Override
    public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException {

    }

    @Override
    public void reSendMessageByMessageId(String messageId) throws MessageBizException {

    }

    @Override
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException {

    }

    @Override
    public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException {
        RpTransactionMessage ret = new RpTransactionMessage();

        ret.setAlreadyDead(UUID.randomUUID().toString());
        ret.setConsumerQueue(UUID.randomUUID().toString());
        ret.setField1("field1");

        return ret;
    }

    @Override
    public void deleteMessageByMessageId(String messageId) throws MessageBizException {

    }

    @Override
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException {

    }
}
