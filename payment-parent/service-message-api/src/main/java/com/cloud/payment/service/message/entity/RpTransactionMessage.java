package com.cloud.payment.service.message.entity;

import com.cloud.payment.common.core.domain.DomainImpl;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
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

}
