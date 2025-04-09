package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PmsAuditLog extends DomainImpl {

    private String opType;
    private String ip;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private PmsUser user;

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PmsUser getUser() {
        return user;
    }

    public void setUser(PmsUser user) {
        this.user = user;
    }
}
