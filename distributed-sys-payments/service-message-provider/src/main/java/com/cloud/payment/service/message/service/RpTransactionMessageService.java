package com.cloud.payment.service.message.service;

import com.cloud.payment.service.message.entity.RpTransactionMessage;

public interface RpTransactionMessageService {
    RpTransactionMessage queryMessage(String messageId);

    RpTransactionMessage saveMessage(RpTransactionMessage message);

    RpTransactionMessage updateMessage(RpTransactionMessage message);

    void deleteMessageById(String messageId);
}
