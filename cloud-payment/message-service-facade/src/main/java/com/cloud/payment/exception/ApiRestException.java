package com.cloud.payment.exception;

import com.cloud.payment.converter.GRpcStatusConverter;
import io.grpc.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class ApiRestException extends RuntimeException {
    private final int statusCode;
    private final String code;
    private final String message;


    public ApiRestException(final Exception e) {
        super(e.getMessage());
        final Status status = Status.fromThrowable(e);
        if (Objects.isNull(status) || Objects.isNull(status.getDescription())) {
            this.statusCode = 500;
            this.code = "INTERNAL_ERROR";
            this.message = "Internal server error";
            return;
        }

        final var errorMessage = status.getDescription().split("\n");
        this.statusCode = GRpcStatusConverter.grpcCodeToHttpStatusCode(status.getCode());
        this.code = errorMessage.length == 2 ? errorMessage[1] : "INTERNAL_ERROR";
        if (List.of(401, 429, 499, 500, 503, 504).contains(this.statusCode)) {
            this.message = GRpcStatusConverter.defaultMessageBuilder(this.statusCode);
            return;
        }
        this.message = errorMessage.length == 2 ? errorMessage[0] : status.getDescription();
    }
}
