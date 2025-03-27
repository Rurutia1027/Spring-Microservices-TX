package com.cloud.payment.service.message.exception;

import com.cloud.payment.common.core.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageBizException extends BizException {
    private static final long serialVersionUID = 87654323010163563L;

    /**
     * Saved Message record is null
     **/
    public static final int SAVED_MESSAGE_IS_NULL = 8001;

    /**
     * Message's MQ is empty.
     **/
    public static final int MESSAGE_CONSUMER_QUEUE_IS_NULL = 8002;

    private static final Log LOG = LogFactory.getLog(MessageBizException.class);

    public MessageBizException() {
    }

    public MessageBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public MessageBizException(int code, String msg) {
        super(code, msg);
    }

    public MessageBizException print() {
        LOG.info("BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
