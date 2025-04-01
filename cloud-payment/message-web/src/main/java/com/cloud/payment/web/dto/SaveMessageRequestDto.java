package com.cloud.payment.web.dto;

import com.cloud.payment.service.message.grpc.RpTransactionMessage;
import com.cloud.payment.service.message.grpc.SaveMessageRequest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
public class SaveMessageRequestDto implements Serializable {
    private static final long serialVersionUID = 34567890L;

    private String messageId;
    private String messageBody;
    private String messageDataType;
    private String consumerQueue;
    private int messageSendTimes;
    private String alreadyDead;
    private String field1;
    private String field2;
    private String field3;

    public SaveMessageRequest convertToSaveMessageRequest() {
        RpTransactionMessage message = RpTransactionMessage.newBuilder()
                .setMessageBody(messageBody)
                .setMessageId(messageId)
                .setConsumerQueue(this.consumerQueue)
                .setField2(UUID.randomUUID().toString())
                .build();

        SaveMessageRequest ret = SaveMessageRequest.newBuilder()
                .setMessage(message)
                .build();

        return ret;
    }
}
