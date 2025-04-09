package com.mini.payment.permission.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PmsMenu extends DomainImpl {
    private String name;
    private String url;
    private String number;
    private Boolean isLeaf;
    private Long level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private PmsMenu parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<PmsMenu> children = new ArrayList<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PmsRoleMenu> menuRoles = new HashSet<>();

    // -- getter && setter --

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public PmsMenu getParent() {
        return parent;
    }

    public void setParent(PmsMenu parent) {
        this.parent = parent;
    }

    public List<PmsMenu> getChildren() {
        return children;
    }

    public void setChildren(List<PmsMenu> children) {
        this.children = children;
    }

    public Set<PmsRoleMenu> getMenuRoles() {
        return menuRoles;
    }

    public void setMenuRoles(Set<PmsRoleMenu> menuRoles) {
        this.menuRoles = menuRoles;
    }
}
