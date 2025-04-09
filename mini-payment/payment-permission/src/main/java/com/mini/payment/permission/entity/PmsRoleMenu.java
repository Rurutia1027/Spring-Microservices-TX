package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class PmsRoleMenu extends DomainImpl {
    @ManyToOne(optional = false)
    private PmsRole role;

    @ManyToOne(optional = false)
    private PmsMenu menu;

    public PmsRole getRole() {
        return role;
    }

    public void setRole(PmsRole role) {
        this.role = role;
    }

    public PmsMenu getMenu() {
        return menu;
    }

    public void setMenu(PmsMenu menu) {
        this.menu = menu;
    }
}
