package com.mini.payment.app.polling.entity;

import java.util.Map;

public class PollingParam {
    private Map<Integer, Integer> notifyParams;
    private String successValue;

    // -- getter && setter --

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

    public Integer getMaxNotifyTimes() {
        return notifyParams == null ? 0 : notifyParams.size();
    }
}
