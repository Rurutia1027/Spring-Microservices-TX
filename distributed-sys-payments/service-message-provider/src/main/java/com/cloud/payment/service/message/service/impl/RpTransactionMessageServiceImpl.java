package com.cloud.payment.service.message.service.impl;

import com.cloud.payment.service.message.entity.RpTransactionMessage;
import com.cloud.payment.service.message.repository.RpTransactionMessageRepository;
import com.cloud.payment.service.message.service.RpTransactionMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RpTransactionMessageServiceImpl implements RpTransactionMessageService {

    @Autowired
    private RpTransactionMessageRepository rpTransactionMessageRepository;


    @Override
    public RpTransactionMessage save(RpTransactionMessage message) {
        return rpTransactionMessageRepository.save(message);
    }

    @Override
    public RpTransactionMessage queryMessage(String messageId) {
        return rpTransactionMessageRepository.findById(Long.parseLong(messageId)).orElse(null);
    }


    @Transactional
    @Override
    public RpTransactionMessage updateMessage(RpTransactionMessage message) {
        return rpTransactionMessageRepository.save(message);
    }

    @Override
    public void deleteMessageById(String messageId) {
        rpTransactionMessageRepository.deleteById(Long.parseLong(messageId));
    }
}
