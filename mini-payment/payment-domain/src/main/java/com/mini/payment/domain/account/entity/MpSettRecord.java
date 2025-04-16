package com.mini.payment.domain.account.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "mp_sett_record")
public class MpSettRecord extends DomainImpl {
    private String settMode;
    private String accountNo;
    private String userNo;
    private String userType;
    private String userName;
    private Date settDate;
    private String bankCode;
    private String bankName;
    private String bankAccountName;
    private String bankAccountNo;
    private String bankAccountType;
    private String bankNationName;
    private String bankCityName;
    private String bankAreas;
    private String bankAccountAddress;
    private String mobile;
    private BigDecimal settAmount = BigDecimal.ZERO;
    private BigDecimal settFee = BigDecimal.ZERO;
    private BigDecimal remitAmount = BigDecimal.ZERO;
    private String settStatus;
    private Date remitRequestTime;
    private Date remitConfirmTime;
    private String remitRemark;
    private String operatorLoginName;
    private String operatorRealName;


    // -- getter && setter --

    public String getSettMode() {
        return settMode;
    }

    public void setSettMode(String settMode) {
        this.settMode = settMode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getSettDate() {
        return settDate;
    }

    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankNationName() {
        return bankNationName;
    }

    public void setBankNationName(String bankNationName) {
        this.bankNationName = bankNationName;
    }

    public String getBankCityName() {
        return bankCityName;
    }

    public void setBankCityName(String bankCityName) {
        this.bankCityName = bankCityName;
    }

    public String getBankAreas() {
        return bankAreas;
    }

    public void setBankAreas(String bankAreas) {
        this.bankAreas = bankAreas;
    }

    public String getBankAccountAddress() {
        return bankAccountAddress;
    }

    public void setBankAccountAddress(String bankAccountAddress) {
        this.bankAccountAddress = bankAccountAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getSettAmount() {
        return settAmount;
    }

    public void setSettAmount(BigDecimal settAmount) {
        this.settAmount = settAmount;
    }

    public BigDecimal getSettFee() {
        return settFee;
    }

    public void setSettFee(BigDecimal settFee) {
        this.settFee = settFee;
    }

    public BigDecimal getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(BigDecimal remitAmount) {
        this.remitAmount = remitAmount;
    }

    public String getSettStatus() {
        return settStatus;
    }

    public void setSettStatus(String settStatus) {
        this.settStatus = settStatus;
    }

    public Date getRemitRequestTime() {
        return remitRequestTime;
    }

    public void setRemitRequestTime(Date remitRequestTime) {
        this.remitRequestTime = remitRequestTime;
    }

    public Date getRemitConfirmTime() {
        return remitConfirmTime;
    }

    public void setRemitConfirmTime(Date remitConfirmTime) {
        this.remitConfirmTime = remitConfirmTime;
    }

    public String getRemitRemark() {
        return remitRemark;
    }

    public void setRemitRemark(String remitRemark) {
        this.remitRemark = remitRemark;
    }

    public String getOperatorLoginName() {
        return operatorLoginName;
    }

    public void setOperatorLoginName(String operatorLoginName) {
        this.operatorLoginName = operatorLoginName;
    }

    public String getOperatorRealName() {
        return operatorRealName;
    }

    public void setOperatorRealName(String operatorRealName) {
        this.operatorRealName = operatorRealName;
    }
}
