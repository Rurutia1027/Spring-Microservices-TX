package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pms_role_permission")
public class PmsRolePermission extends DomainImpl {
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private PmsRole role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "permission_id")
    private PmsPermission permission;

    public PmsRole getRole() {
        return role;
    }

    public void setRole(PmsRole role) {
        this.role = role;
    }

    public PmsPermission getPermission() {
        return permission;
    }

    public void setPermission(PmsPermission permission) {
        this.permission = permission;
    }
}
