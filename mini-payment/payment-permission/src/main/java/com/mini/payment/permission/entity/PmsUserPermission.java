package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pms_user_permission")
public class PmsUserPermission extends DomainImpl {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private PmsUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "permission_id")
    private PmsPermission permission;

    public PmsUser getUser() {
        return user;
    }

    public void setUser(PmsUser user) {
        this.user = user;
    }

    public PmsPermission getPermission() {
        return permission;
    }

    public void setPermission(PmsPermission permission) {
        this.permission = permission;
    }
}
