package com.mini.payment.domain.trade.exception;

import com.mini.payment.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TradeBizException extends BizException {
    /**
     * error payment
     **/
    public static final int TRADE_PAY_WAY_ERROR = 102;

    /**
     * wechat payment error
     **/
    public static final int TRADE_WECHAT_ERROR = 103;
    /**
     * order error
     **/
    public static final int TRADE_ORDER_ERROR = 104;

    /**
     * trade order status not success
     **/
    public static final int TRADE_ORDER_STATUS_NOT_SUCCESS = 105;

    /**
     * Alipay error
     **/
    public static final int TRADE_ALIPAY_ERROR = 106;

    /**
     * trade param error
     **/
    public static final int TRADE_PARAM_ERROR = 107;

    private static final Log LOG = LogFactory.getLog(TradeBizException.class);

    public TradeBizException() {
    }

    public TradeBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public TradeBizException(int code, String msg) {
        super(code, msg);
    }

    public TradeBizException print() {
        LOG.error("BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}