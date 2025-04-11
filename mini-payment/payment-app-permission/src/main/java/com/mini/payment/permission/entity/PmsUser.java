package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pms_user")
public class PmsUser extends DomainImpl {
    @Column(unique = true, nullable = false)
    private String loginName;

    @Column(unique = true, nullable = false)
    private String loginPwd;

    private String realName;

    private String mobileNo;

    private String email;

    private String type;

    private String salt;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PmsUserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PmsUserPermission> userPermissions = new HashSet<>();

    public PmsUser() {
    }

    // -- getter && setter --
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<PmsUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<PmsUserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PmsUserPermission> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Set<PmsUserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
