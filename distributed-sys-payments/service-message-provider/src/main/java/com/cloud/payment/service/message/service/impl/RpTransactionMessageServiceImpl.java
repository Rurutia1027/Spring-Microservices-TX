package com.cloud.payment.service.message.service.impl;

import com.cloud.payment.service.message.entity.RpTransactionMessage;
import com.cloud.payment.service.message.repository.RpTransactionMessageRepository;
import com.cloud.payment.service.message.service.RpTransactionMessageService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
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
            log.warn("Received an invalid message, cannot save it to db!");
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
            log.warn("Received message id that gonna to be deleted is invalid!");
            return;
        }

        Optional<RpTransactionMessage> optionalMessage =
                rpTransactionMessageRepository.findById(Long.parseLong(messageId));

        if (!optionalMessage.isPresent()) {
            log.warn("Cannot find message entity from DB by given message ID {}", messageId);
            return;
        }

        rpTransactionMessageRepository.delete(optionalMessage.get());
        rpTransactionMessageRepository.flush();
    }
}
