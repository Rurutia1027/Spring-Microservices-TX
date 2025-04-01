package com.cloud.payment.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for RpTransactionMessage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpTransactionMessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

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