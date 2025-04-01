package com.cloud.payment.exception;

import io.grpc.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GRpcException extends RuntimeException {
    private final Status.Code code;
    private final String codeMessage;
    private final String message;

    public GRpcException(final Status.Code code, final String codeMessage,
                         final String message) {
        super(message);
        this.code = code;
        this.codeMessage = codeMessage;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final GRpcException that)) return false;

        if (getCode() != that.getCode()) return false;
        if (getCodeMessage() != null ? !getCodeMessage().equals(that.getCodeMessage()) : that.getCodeMessage() != null)
            return false;
        return getMessage() != null ? getMessage().equals(that.getMessage()) : that.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getCodeMessage() != null ? getCodeMessage().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }
}
