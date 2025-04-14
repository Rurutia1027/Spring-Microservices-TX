package com.mini.payment.trade.enums;

public enum AuthStatusEnum {

    SUCCESS("authenticate success"),

    WAITING_AUTH("authenticating "),

    FAILED("authenticate failure");

    private AuthStatusEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
