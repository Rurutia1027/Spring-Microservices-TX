package com.mini.payment.app.notify.entity;


import java.util.Map;
import java.util.Objects;

public class NotifyStrategy {
    private Map<Integer, Integer> notifyParams;
    private String successValue;

    public Map<Integer, Integer> getNotifyParams() {
        return notifyParams;
    }

    public void setNotifyParams(Map<Integer, Integer> notifyParams) {
        this.notifyParams = notifyParams;
    }

    public String getSuccessValue() {
        return successValue;
    }

    public void setSuccessValue(String successValue) {
        this.successValue = successValue;
    }

    public Integer getMaxNotifyTimeLimitation() {
        return Objects.isNull(notifyParams) ? 0 : notifyParams.size();
    }
}
