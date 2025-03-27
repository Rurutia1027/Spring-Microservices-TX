package com.cloud.payment.service.message.service;

import com.cloud.payment.service.message.entity.RpTransactionMessage;

public interface RpTransactionMessageService {
    RpTransactionMessage save(RpTransactionMessage message);

    RpTransactionMessage queryMessage(String messageId);

    RpTransactionMessage updateMessage(RpTransactionMessage message);

    void deleteMessageById(String messageId);
}
