package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pms_role")
public class PmsRole extends DomainImpl {
    private String roleCode;
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PmsUserRole> roleUsers = new HashSet<>();

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PmsRolePermission> rolePermissions;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PmsRoleMenu> roleMenus = new HashSet<>();


    // --- getter & setter ---

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<PmsUserRole> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<PmsUserRole> roleUsers) {
        this.roleUsers = roleUsers;
    }

    public Set<PmsRolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<PmsRolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public Set<PmsRoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Set<PmsRoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }
}
