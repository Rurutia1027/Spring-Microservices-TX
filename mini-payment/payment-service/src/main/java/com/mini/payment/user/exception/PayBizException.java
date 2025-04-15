package com.mini.payment.user.exception;

import com.mini.payment.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PayBizException extends BizException {

    /**
     *
     */
    private static final long serialVersionUID = -71631262025757L;

    public static final int PAY_TYPE_IS_EXIST = 101;


    public static final int PAY_PRODUCT_IS_EXIST = 102;

    public static final int PAY_PRODUCT_HAS_DATA = 103;


    public static final int USER_PAY_CONFIG_IS_EXIST = 104;


    public static final int USER_PAY_CONFIG_IS_NOT_EXIST = 105;


    public static final int USER_PAY_CONFIG_IS_EFFECTIVE = 106;

    public static final int PAY_PRODUCT_IS_EFFECTIVE = 107;

    public static final int PAY_PRODUCT_IS_NOT_EXIST = 108;


    public static final int PAY_TYPE_IS_NOT_EXIST = 109;


    public static final int REQUEST_PARAM_ERR = 110;


    public static final int REQUEST_BANK_ERR = 111;

    private static final Log LOG = LogFactory.getLog(PayBizException.class);

    public PayBizException() {
    }

    public PayBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public PayBizException(int code, String msg) {
        super(code, msg);
    }

    public PayBizException print() {
        LOG.info("BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
