package com.cloud.payment.service.message.api.impl;

import com.cloud.payment.common.core.enums.PublicEnum;
import com.cloud.payment.service.message.api.RpcRpTransactionMessageService;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import com.cloud.payment.service.message.enums.MessageStatusEnum;
import com.cloud.payment.service.message.exception.MessageBizException;
import com.cloud.payment.service.message.service.MessageQueueService;
import com.cloud.payment.service.message.service.RpTransactionMessageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.Objects;

@DubboService
public class RpcRpTransactionMessageServiceImpl implements RpcRpTransactionMessageService {
    @Autowired
    private RpTransactionMessageService rpTransactionMessageService;

    @Autowired
    @Qualifier("kafkaMQMessageQueueService")
    MessageQueueService kafkaMQMessageQueueService;

    @Autowired
    @Qualifier("activeMQMessageQueueService")
    MessageQueueService activeMQMessageQueueService;


    @Override
    public int saveMessageWaitingConfirm(RpTransactionMessage message) throws MessageBizException {
        if (Objects.isNull(message)) {
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL,
                    "Received to be saved message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL
                    , "Received message's consumer queue is empty!");
        }

        message.setEditTime(new Date());
        message.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
        message.setAlreadyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        RpTransactionMessage ret = rpTransactionMessageService.saveMessage(message);

        if (ret.getId() > 0L) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MessageBizException {
        final RpTransactionMessage message =
                rpTransactionMessageService.queryMessage(messageId);

        if (Objects.isNull(message)) {
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL,
                    "Cannot find message entity from database by the given messageId" + messageId);
        }

        // message status from To Be Confirm -> Sending
        message.setStatus(MessageStatusEnum.SENDING.name());
        // update timestamp for message
        message.setEditTime(new Date());

        // refresh message record to db
        rpTransactionMessageService.updateMessage(message);

        // todo: send message to active|kafka queue
        activeMQMessageQueueService.sendMessage("topic", "message status");
    }

    @Override
    public int saveAndSendMessage(RpTransactionMessage message) throws MessageBizException {
        if (Objects.isNull(message)) {
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL, "To be " +
                    "saved message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL
                    , "Message to be consumed message queue is not defined");
        }

        message.setStatus(MessageStatusEnum.SENDING.name());
        message.setAlreadyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        message.setEditTime(new Date());
        RpTransactionMessage ret = rpTransactionMessageService.updateMessage(message);

        // send message queue message here
        return Objects.nonNull(ret) && ret.getId() > 0 ? 0 : -1;
    }

    // Sending message out directly without store message to database table
    @Override
    public void directSendMessage(RpTransactionMessage message) throws MessageBizException {
        if (Objects.isNull(message)) {
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL,
                    "Received to be saved message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL
                    , "Received msg consume queue is not defined!");
        }

        // todo: send message to message queue
    }

    @Override
    public void reSendMessage(final RpTransactionMessage message) throws MessageBizException {
        if (Objects.isNull(message)) {
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL,
                    "Received message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL
                    , "Received message's consumer queue name is empty");
        }

        message.addSendTimes();
        message.setEditTime(new Date());
        rpTransactionMessageService.updateMessage(message);

        // todo: send jms message
    }

    @Override
    public void reSendMessageByMessageId(String messageId) throws MessageBizException {
        final RpTransactionMessage message =
                rpTransactionMessageService.queryMessage(messageId);

        if (Objects.isNull(message)) {
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL, "Cannot" +
                    " find message entity from db by given messageId " + messageId);
        }

        // todo: refine this latter, set max retry times to config file

        int maxReSendTimes = 5;
        if (message.getMessageSendTimes() >= maxReSendTimes) {
            message.setAlreadyDead(PublicEnum.YES.name());
        }

        message.setEditTime(new Date());
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);
        rpTransactionMessageService.updateMessage(message);

        // todo: send jms message
    }

    @Override
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException {
        RpTransactionMessage message = rpTransactionMessageService.queryMessage(messageId);

        if (Objects.isNull(message)) {
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL, "Cannot" +
                    " find message entity from db by given messageId " + messageId);
        }

        message.setAlreadyDead(PublicEnum.YES.name());
        message.setEditTime(new Date());
        rpTransactionMessageService.updateMessage(message);
    }

    @Override
    public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException {
        return rpTransactionMessageService.queryMessage(messageId);
    }

    @Override
    public void deleteMessageByMessageId(String messageId) throws MessageBizException {
        rpTransactionMessageService.deleteMessageById(messageId);
    }


    // todo: refine this function, after implementing the page list logic in Message
    //  Repository layer
    @Override
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException {
        // todo: num per page base value should be load from config page, refine this next time
        int numPerPage = 1000;
        if (batchSize > 0 && batchSize < 100) {
            numPerPage = 100;
        } else if (batchSize > 100 && batchSize < 5000) {
            numPerPage = batchSize;
        } else if (batchSize > 5000) {
            numPerPage = 5000;
        } else {
            numPerPage = 1000;
        }
    }
}
