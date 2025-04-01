package com.cloud.payment.web.dto;

import com.cloud.payment.service.message.grpc.SaveMessageResponse;
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
    private static final long serialVersionUID = 98765432L;

    private Integer status;

    // Method to convert gRPC response to DTO
    public SaveMessageResponseDto convertToSaveMessageResponse(SaveMessageResponse saveMessageResponse) {
        // Map the gRPC message's status field to the DTO's status field
        this.status = saveMessageResponse.getStatus();
        return this;
    }
}