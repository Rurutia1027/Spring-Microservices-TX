package com.mini.payment.domain.notify.entity;

import com.alibaba.fastjson.JSONObject;
import com.mini.payment.domain.notify.enums.NotifyStatusEnum;
import com.mini.payment.domain.DomainImpl;

import java.util.Date;
import java.util.Map;

public class MpOrderResultQueryVo extends DomainImpl {
    private String notifyStrategy;
    private Date lastNotifyTime;
    private Integer notifyTimes;
    private Integer limitNotifyTimes;
    private String bankOrderNo;

    public MpOrderResultQueryVo() {
        super();
    }

    public MpOrderResultQueryVo(Date createTime, String notifyStrategy, Date lastNotifyTime,
                                Integer notifyTimes, Integer limitNotifyTimes,
                                String bankOrderNo, NotifyStatusEnum status) {
        super();
        super.setCreateTime(createTime);
        super.setStatus(status.name());
        this.notifyStrategy = notifyStrategy;
        this.lastNotifyTime = lastNotifyTime;
        this.notifyTimes = notifyTimes;
        this.limitNotifyTimes = limitNotifyTimes;
        this.bankOrderNo = bankOrderNo;
    }

    public Map<Integer, Integer> getNotifyStrategyMap() {
        return (Map) JSONObject.parseObject(getNotifyStrategy());
    }

    // -- getter && setter --

    public String getNotifyStrategy() {
        return notifyStrategy;
    }

    public void setNotifyStrategy(String notifyStrategy) {
        this.notifyStrategy = notifyStrategy;
    }

    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    public void setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }

    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }
}
