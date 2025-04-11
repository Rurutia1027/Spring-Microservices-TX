package com.mini.payment.notify.entity;

import com.mini.payment.domain.DomainImpl;
import com.mini.payment.notify.enums.NotifyStatusEnum;
import com.mini.payment.notify.enums.NotifyTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "notify_record")
public class MpNotifyRecord extends DomainImpl {
    private Date lastNotifyTime;
    private Integer notifyTimes;
    private Integer limitNotifyTimes;
    // notify endpoint url
    private String url;
    private String merchantNo;
    private String merchantOrderNo;
    private String notifyType;

    public MpNotifyRecord() {
    }

    public MpNotifyRecord(Date createTime, Date lastNotifyTime, Integer notifyTimes,
                          String url, String merchantNo, String merchantOrderNo,
                          NotifyStatusEnum status,
                          NotifyTypeEnum notifyType) {
        super();
        super.setCreateTime(createTime);
        super.setStatus(status.name());
        this.lastNotifyTime = lastNotifyTime;
        this.notifyTimes = notifyTimes;
        this.url = url;
        this.merchantNo = merchantNo;
        this.merchantOrderNo = merchantOrderNo;
        this.notifyType = notifyType.name();
    }

    // getter && setter

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }

    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }
}
