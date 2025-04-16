package com.mini.payment.domain.user.exception;

import com.mini.payment.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UserBizException extends BizException {

    private static final long serialVersionUID = -45868682L;

    public static final int USER_IS_NULL = 101;

    public static final int USER_PAY_CONFIG_ERROR = 102;

    public static final UserBizException USER_BANK_ACCOUNT_IS_NULL = new UserBizException(10010002, "用户未设置银行账户信息!");

    private static final Log LOG = LogFactory.getLog(UserBizException.class);

    public UserBizException() {
    }

    public UserBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public UserBizException(int code, String msg) {
        super(code, msg);
    }

    public UserBizException print() {
        LOG.info("BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
