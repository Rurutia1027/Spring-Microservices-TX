package com.mini.payment.domains.account.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "mp_account")
@Data
public class MpAccount extends DomainImpl {
    private String accountNo;
    private BigDecimal balance;
    private BigDecimal unbalance;
    private BigDecimal securityMoney;
    private BigDecimal totalIncome;
    private BigDecimal totalExpend;
    private BigDecimal todayIncome;
    private BigDecimal todayExpand;
    private BigDecimal accountType;
    private BigDecimal settAmount;
    private String userNo;
    private String userName;

    // -- getter && setter --

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUnbalance() {
        return unbalance;
    }

    public void setUnbalance(BigDecimal unbalance) {
        this.unbalance = unbalance;
    }

    public BigDecimal getSecurityMoney() {
        return securityMoney;
    }

    public void setSecurityMoney(BigDecimal securityMoney) {
        this.securityMoney = securityMoney;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpend() {
        return totalExpend;
    }

    public void setTotalExpend(BigDecimal totalExpend) {
        this.totalExpend = totalExpend;
    }

    public BigDecimal getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(BigDecimal todayIncome) {
        this.todayIncome = todayIncome;
    }

    public BigDecimal getTodayExpand() {
        return todayExpand;
    }

    public void setTodayExpand(BigDecimal todayExpand) {
        this.todayExpand = todayExpand;
    }

    public BigDecimal getAccountType() {
        return accountType;
    }

    public void setAccountType(BigDecimal accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getSettAmount() {
        return settAmount;
    }

    public void setSettAmount(BigDecimal settAmount) {
        this.settAmount = settAmount;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
