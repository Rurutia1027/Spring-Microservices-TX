package com.mini.payment.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -7654321226068L;

    /**
     * database operation, insert return 0
     */
    public static final BizException DB_INSERT_RESULT_0 = new BizException(
            10040001, "DB Operation [insert] return 0"
    );

    /**
     * database operation, update return 0
     */
    public static final BizException DB_UPDATE_RESULT_0 = new BizException(
            10040002, "DB Operation [update] return 0");

    /**
     * database operation, selectOne return null
     */
    public static final BizException DB_SELECTONE_IS_NULL = new BizException(
            10040003, "DB Operation,[selectOne] return null");

    /**
     * database operation, list return null
     */
    public static final BizException DB_LIST_IS_NULL = new BizException(
            10040004, "DB Operation,[list] return null");

    /**
     * Token Validation Failure
     */
    public static final BizException TOKEN_IS_ILLICIT = new BizException(
            10040005, "Token Validation Failure");
    /**
     * Session Timeout, when we retrieve session, if return null,
     * then exception below will be thrown.
     */
    public static final BizException SESSION_IS_OUT_TIME = new BizException(
            10040006, "Session Timeout");

    /**
     * Timeout when generating sequence id
     */
    public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(
            10040007, "Sequence Generation Timeout");


    // ---
    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public BizException() {
        super();
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    protected String msg;
    protected int code;

    public BizException getInstance(String msgFormat, Object... args) {
        return new BizException(this.code, msgFormat, args);
    }
}
