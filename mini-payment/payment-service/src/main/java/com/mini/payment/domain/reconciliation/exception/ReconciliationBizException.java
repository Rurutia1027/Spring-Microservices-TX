package com.mini.payment.domain.reconciliation.exception;

import com.mini.payment.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * reconciliation business exception
 */
public class ReconciliationBizException extends BizException {


    public static final int TRADE_ORDER_NO_EXIST = 20020001;

    private static final Log LOG = LogFactory.getLog(ReconciliationBizException.class);

    public ReconciliationBizException() {
    }

    public ReconciliationBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public ReconciliationBizException(int code, String msg) {
        super(code, msg);
    }

    public ReconciliationBizException print() {
        LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
