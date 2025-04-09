package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pms_user_role")
public class PmsUserRole extends DomainImpl {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private PmsUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private PmsRole role;

    public PmsUser getUser() {
        return user;
    }

    public void setUser(PmsUser user) {
        this.user = user;
    }

    public PmsRole getRole() {
        return role;
    }

    public void setRole(PmsRole role) {
        this.role = role;
    }
}
