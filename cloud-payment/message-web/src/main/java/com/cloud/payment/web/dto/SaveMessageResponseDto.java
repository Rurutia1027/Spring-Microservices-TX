package com.cloud.payment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for SaveMessageResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveMessageResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
}