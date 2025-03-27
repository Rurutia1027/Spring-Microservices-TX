package com.cloud.payment.service.message.service.impl;

import com.cloud.payment.service.message.entity.RpTransactionMessage;
import com.cloud.payment.service.message.repository.RpTransactionMessageRepository;
import com.cloud.payment.service.message.service.RpTransactionMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class RpTransactionMessageServiceImpl implements RpTransactionMessageService {

    @Autowired
    private RpTransactionMessageRepository rpTransactionMessageRepository;


    @Override
    public RpTransactionMessage queryMessage(String messageId) {
        RpTransactionMessage ret =
                rpTransactionMessageRepository.findById(Long.parseLong(messageId)).orElse(null);
        return ret;
    }

    @Override
    public RpTransactionMessage saveMessage(RpTransactionMessage message) {
        if (Objects.isNull(message)) {
            return null;
        }

        RpTransactionMessage ret = rpTransactionMessageRepository.save(message);
        return ret;
    }


    @Transactional
    @Override
    public RpTransactionMessage updateMessage(RpTransactionMessage message) {
        return rpTransactionMessageRepository.save(message);
    }

    @Transactional
    @Override
    public void deleteMessageById(String messageId) {
        if (Objects.isNull(messageId) || StringUtils.isEmpty(messageId)) {
            return;
        }

        Optional<RpTransactionMessage> optionalMessage =
                rpTransactionMessageRepository.findById(Long.parseLong(messageId));

        if (!optionalMessage.isPresent()) {
            return;
        }

        rpTransactionMessageRepository.delete(optionalMessage.get());
        rpTransactionMessageRepository.flush();
    }
}
