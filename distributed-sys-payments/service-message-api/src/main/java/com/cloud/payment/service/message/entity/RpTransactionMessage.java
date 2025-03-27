package com.cloud.payment.service.message.entity;

import com.cloud.payment.common.core.domain.DomainImpl;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RpTransactionMessage extends DomainImpl {
    private static final long serialVersionUID = 5263748557814546156L;

    private String messageId;

    private String messageBody;

    private String messageDataType;

    private String consumerQueue;

    private Integer messageSendTimes;

    private String alreadyDead;

    private String field1;

    private String field2;

    private String field3;


    public void addSendTimes() {
        messageSendTimes++;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageDataType() {
        return messageDataType;
    }

    public void setMessageDataType(String messageDataType) {
        this.messageDataType = messageDataType;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    public Integer getMessageSendTimes() {
        return messageSendTimes;
    }

    public void setMessageSendTimes(Integer messageSendTimes) {
        this.messageSendTimes = messageSendTimes;
    }

    public String getAlreadyDead() {
        return alreadyDead;
    }

    public void setAlreadyDead(String alreadyDead) {
        this.alreadyDead = alreadyDead;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }
}
