package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "pms_permission")
public class PmsPermission extends DomainImpl {

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PmsRolePermission> permissionRoles;

    private String permissionName;
    private String permission;


    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


    public Set<PmsRolePermission> getPermissionRoles() {
        return permissionRoles;
    }

    public void setPermissionRoles(Set<PmsRolePermission> permissionRoles) {
        this.permissionRoles = permissionRoles;
    }
}
