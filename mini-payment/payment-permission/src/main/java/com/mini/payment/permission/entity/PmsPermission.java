package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.Set;

@Entity
@Table(name = "pms_permission")
public class PmsPermission extends DomainImpl {

    @OneToMany(mappedBy = "permission", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PmsRolePermission> permissionRoles;

    @OneToMany(mappedBy = "permission", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PmsUserPermission> pmsUserPermissions;

    private String resource;
    private String action;
    private String scope;
    private String permission;

    @Transient
    public String getPermCode() {
        return scope != null && !scope.isEmpty()
                ? String.format("%s:%s:%s", scope, resource, action)
                : String.format("%s:%s", resource, action);
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String permissionName) {
        this.resource = permissionName;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Set<PmsUserPermission> getPmsUserPermissions() {
        return pmsUserPermissions;
    }

    public void setPmsUserPermissions(Set<PmsUserPermission> pmsUserPermissions) {
        this.pmsUserPermissions = pmsUserPermissions;
    }
}
