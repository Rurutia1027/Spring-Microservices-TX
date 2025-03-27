package com.cloud.payment.service.message.api;

import com.cloud.payment.service.message.entity.RpTransactionMessage;
import com.cloud.payment.service.message.exception.MessageBizException;


public interface RpcRpTransactionMessageService {
    /**
     * Pre-save message with status as 'to-be-confirmed'
     */
    int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * Send confirmed message via given messageId
     */
    void confirmAndSendMessage(String messageId) throws MessageBizException;


    /**
     * Save && send message via provided message entity
     */
    int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * Re-sending message directly
     */
    void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * Re-sending messages via the given message entity
     */
    void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;


    /**
     * Re-send message via the given @param messageId
     */
    void reSendMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * Update the message status as Dead via provided messageId.
     */
    void setMessageToAreadlyDead(String messageId) throws MessageBizException;


    /**
     * Query message entity from storage layer(database) via given messageId
     */
    RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException;

    /**
     * ¬
     * Delete message from storage layer(database) by given messageId
     */
    void deleteMessageByMessageId(String messageId) throws MessageBizException;


    /**
     * Resending all dead messages that locates in the given queue name
     */
    void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws MessageBizException;

    /**
     * Fetch datasets via pages
     */
//    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) throws MessageBizException;


}
