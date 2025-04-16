package com.mini.payment.domain.reconciliation.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "mp_account_check_batch")
public class MpAccountCheckBatch extends DomainImpl {
    private String batchNo;
    private Date billDate;
    private String billType;
    private String handleStatus;
    // WEChat, Alipay
    private String bankType;
    private Integer mistakeCount;
    private Integer unhandledMistakeCount;
    private Integer tradeCount;
    private Integer bankTradeCount;
    private BigDecimal tradeAmount;
    private BigDecimal bankTradeAmount;
    private BigDecimal refundAmount;
    private BigDecimal bankRefundAmount;
    private BigDecimal platformFee;
    private BigDecimal bankFee;
    private String bankErrMsg;

    // -- getter && setter --
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getMistakeCount() {
        return mistakeCount;
    }

    public void setMistakeCount(Integer mistakeCount) {
        this.mistakeCount = mistakeCount;
    }

    public Integer getUnhandledMistakeCount() {
        return unhandledMistakeCount;
    }

    public void setUnhandledMistakeCount(Integer unhandledMistakeCount) {
        this.unhandledMistakeCount = unhandledMistakeCount;
    }

    public Integer getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(Integer tradeCount) {
        this.tradeCount = tradeCount;
    }

    public Integer getBankTradeCount() {
        return bankTradeCount;
    }

    public void setBankTradeCount(Integer bankTradeCount) {
        this.bankTradeCount = bankTradeCount;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getBankTradeAmount() {
        return bankTradeAmount;
    }

    public void setBankTradeAmount(BigDecimal bankTradeAmount) {
        this.bankTradeAmount = bankTradeAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getBankRefundAmount() {
        return bankRefundAmount;
    }

    public void setBankRefundAmount(BigDecimal bankRefundAmount) {
        this.bankRefundAmount = bankRefundAmount;
    }

    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
    }

    public BigDecimal getBankFee() {
        return bankFee;
    }

    public void setBankFee(BigDecimal bankFee) {
        this.bankFee = bankFee;
    }

    public String getBankErrMsg() {
        return bankErrMsg;
    }

    public void setBankErrMsg(String bankErrMsg) {
        this.bankErrMsg = bankErrMsg;
    }
}
