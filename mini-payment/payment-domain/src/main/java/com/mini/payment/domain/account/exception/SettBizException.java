package com.mini.payment.domain.account.exception;

import com.mini.payment.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SettBizException extends BizException {

    private static final long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(SettBizException.class);

    public static final SettBizException SETT_STATUS_ERROR =
            new SettBizException(10010001, "settle error");

    public SettBizException() {
    }

    public SettBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public SettBizException(int code, String msg) {
        super(code, msg);
    }

    public SettBizException newInstance(String msgFormat, Object... args) {
        return new SettBizException(this.code, msgFormat, args);
    }

    public SettBizException print() {
        LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
