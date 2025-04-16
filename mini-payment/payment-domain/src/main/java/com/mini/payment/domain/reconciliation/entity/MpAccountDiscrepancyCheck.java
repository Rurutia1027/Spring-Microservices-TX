package com.mini.payment.domain.reconciliation.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "mp_account_discrepancy_check")
public class MpAccountDiscrepancyCheck extends DomainImpl {
    private String accountCheckBatchNo;
    private Date billDate;
    private String bankType;
    private Date orderTime;
    private String merchantName;
    private String merchantNo;
    private String orderNo;
    private Date tradeTime;
    private String trxNo;
    private BigDecimal orderAmount;
    private BigDecimal refundAmount;
    private String tradeStatus;
    private BigDecimal platformFee;
    private Date bankTradeTime;
    private String bankOrderNo;
    private String bankTrxNo;
    private String bankTradeStatus;
    private BigDecimal bankAmount;
    private BigDecimal bankRefundAmount;
    private BigDecimal bankFee;
    private String errType;
    private String handleStatus;
    private String handleValue;
    private String handleDescription;
    private String operatorName;
    private String operatorAccountNo;

    // -- getter && setter --

    public String getAccountCheckBatchNo() {
        return accountCheckBatchNo;
    }

    public void setAccountCheckBatchNo(String accountCheckBatchNo) {
        this.accountCheckBatchNo = accountCheckBatchNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
    }

    public Date getBankTradeTime() {
        return bankTradeTime;
    }

    public void setBankTradeTime(Date bankTradeTime) {
        this.bankTradeTime = bankTradeTime;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public String getBankTrxNo() {
        return bankTrxNo;
    }

    public void setBankTrxNo(String bankTrxNo) {
        this.bankTrxNo = bankTrxNo;
    }

    public String getBankTradeStatus() {
        return bankTradeStatus;
    }

    public void setBankTradeStatus(String bankTradeStatus) {
        this.bankTradeStatus = bankTradeStatus;
    }

    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
    }

    public BigDecimal getBankRefundAmount() {
        return bankRefundAmount;
    }

    public void setBankRefundAmount(BigDecimal bankRefundAmount) {
        this.bankRefundAmount = bankRefundAmount;
    }

    public BigDecimal getBankFee() {
        return bankFee;
    }

    public void setBankFee(BigDecimal bankFee) {
        this.bankFee = bankFee;
    }

    public String getErrType() {
        return errType;
    }

    public void setErrType(String errType) {
        this.errType = errType;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleValue() {
        return handleValue;
    }

    public void setHandleValue(String handleValue) {
        this.handleValue = handleValue;
    }

    public String getHandleDescription() {
        return handleDescription;
    }

    public void setHandleDescription(String handleDescription) {
        this.handleDescription = handleDescription;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorAccountNo() {
        return operatorAccountNo;
    }

    public void setOperatorAccountNo(String operatorAccountNo) {
        this.operatorAccountNo = operatorAccountNo;
    }
}
