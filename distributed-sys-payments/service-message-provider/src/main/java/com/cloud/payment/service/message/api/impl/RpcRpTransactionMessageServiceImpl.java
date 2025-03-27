package com.cloud.payment.service.message.api.impl;

import com.cloud.payment.common.core.enums.PublicEnum;
import com.cloud.payment.service.message.api.RpcRpTransactionMessageService;
import com.cloud.payment.service.message.entity.RpTransactionMessage;
import com.cloud.payment.service.message.enums.MessageStatusEnum;
import com.cloud.payment.service.message.exception.MessageBizException;
import com.cloud.payment.service.message.service.RpTransactionMessageService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Log4j2
@DubboService
public class RpcRpTransactionMessageServiceImpl implements RpcRpTransactionMessageService {


    @Autowired
    private RpTransactionMessageService rpTransactionMessageService;


    @Override
    public int saveMessageWaitingConfirm(RpTransactionMessage message) throws MessageBizException {
        log.info("receive message and gonna save it to database table");

        if (Objects.isNull(message)) {
            log.warn("Receive an invalid message!");
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL,
                    "Received to be saved message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            log.warn("Receive a consumer queue empty message! ");
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL
                    , "Received message's consumer queue is empty!");
        }

        message.setEditTime(new Date());
        message.setStatus(MessageStatusEnum.WAITING_CONFIRM.name());
        message.setAlreadyDead(PublicEnum.NO.name());
        message.setMessageSendTimes(0);
        RpTransactionMessage ret = rpTransactionMessageService.saveMessage(message);

        if (ret.getId() > 0L) {
            log.info("Received Message saved to db successfully");
            return 0;
        } else {
            log.error("Received Message failed to save to db");
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

        log.info("Fetch message successfully via given messageId " + messageId);
        // message status from To Be Confirm -> Sending
        message.setStatus(MessageStatusEnum.SENDING.name());
        // update timestamp for message
        message.setEditTime(new Date());

        // refresh message record to db
        rpTransactionMessageService.updateMessage(message);

        // todo: send message here
    }

    @Override
    public int saveAndSendMessage(RpTransactionMessage message) throws MessageBizException {
        if (Objects.isNull(message)) {
            log.warn("Received to be saved & sent message is null!");
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL, "To be " +
                    "saved message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            log.warn("Received to be saved message's consume queue name is empty!");
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
            log.warn("Received message is null!");
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL,
                    "Received to be saved message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            log.warn("Received message's message consume queue is not declared !");
            throw new MessageBizException(MessageBizException.MESSAGE_CONSUMER_QUEUE_IS_NULL
                    , "Received msg consume queue is not defined!");
        }

        // todo: send message to message queue
    }

    @Override
    public void reSendMessage(final RpTransactionMessage message) throws MessageBizException {
        if (Objects.isNull(message)) {
            log.warn("Received message is null!");
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL,
                    "Received message is null!");
        }

        if (StringUtils.isEmpty(message.getConsumerQueue())) {
            log.warn("Received message's consumer queue name is empty!");
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
            log.warn("Received message id {} cannot find corresponding record in db",
                    messageId);
            throw new MessageBizException(MessageBizException.SAVED_MESSAGE_IS_NULL, "Cannot" +
                    " find message entity from db by given messageId " + messageId);
        }

        // todo: refine this latter, set max retry times to config file

        int maxReSendTimes = 5;
        if (message.getMessageSendTimes() >= maxReSendTimes) {
            log.warn("Message entity with id {} send times {} larger than maximum retry " +
                            "times {} set it's status to dead", messageId,
                    message.getMessageSendTimes(), maxReSendTimes);
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
            log.warn("Cannot find message entity by given id {}", messageId);
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
        log.info("Message with id {} will be deleted!", messageId);
        rpTransactionMessageService.deleteMessageById(messageId);
    }


    // todo: refine this function, after implementing the page list logic in Message
    //  Repository layer
    @Override
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException {
        log.info("ReSend all dead messages in given queue name {} with batch size {}",
                queueName, batchSize);

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


        log.info("NumPerPage value set to {}", numPerPage);

    }
}
