package com.cloud.payment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for QueueNameRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueNameRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String queueName;
    private Integer batchSize;
}