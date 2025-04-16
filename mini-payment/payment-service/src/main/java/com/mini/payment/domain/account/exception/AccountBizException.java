package com.mini.payment.domain.account.exception;

import com.mini.payment.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountBizException extends BizException {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AccountBizException.class);

    public static final AccountBizException ACCOUNT_NOT_EXIST = new AccountBizException(10010001, "account not exist");
    public static final AccountBizException ACCOUNT_SUB_AMOUNT_EXCEEDS_LIMIT = new AccountBizException(10010002, "Insufficient balance, deduction exceeds limit");
    public static final AccountBizException ACCOUNT_UN_FROZEN_AMOUNT_EXCEEDS_LIMIT = new AccountBizException(10010003, "Unfrozen amount exceeds limit");
    public static final AccountBizException ACCOUNT_FROZEN_AMOUNT_EXCEEDS_LIMIT = new AccountBizException(10010004, "Frozen amount exceeds limit");
    public static final AccountBizException ACCOUNT_TYPE_IS_NULL = new AccountBizException(10010005, "account type cannot be null");

    public AccountBizException() {
    }

    public AccountBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public AccountBizException(int code, String msg) {
        super(code, msg);
    }

    public AccountBizException newInstance(String msgFormat, Object... args) {
        return new AccountBizException(this.code, msgFormat, args);
    }

    public AccountBizException print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
