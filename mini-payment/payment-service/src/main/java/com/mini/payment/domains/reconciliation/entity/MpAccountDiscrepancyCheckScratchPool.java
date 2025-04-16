package com.mini.payment.domains.reconciliation.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "mp_account_discrepancy_check_scratch_pool")
public class MpAccountDiscrepancyCheckScratchPool extends DomainImpl {
    private String productName;
    private String merchantOrderNo;
    private String trxNo;
    private String bankOrderNo;
    private String bankTrxNo;
    private BigDecimal orderAmount;
    private BigDecimal platIncome;
    private BigDecimal feeRate;
    private BigDecimal platCost;
    private BigDecimal platProfit;
    private String payWayCode;
    private String payWayName;
    private Date paySuccessTime;
    private Date completeTime;
    private String isRefund;
    private Short refundTimes;
    private BigDecimal successRefundAmount;
    private String batchNo;
    private Date billDate;

    // -- getter && setter --

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPlatIncome() {
        return platIncome;
    }

    public void setPlatIncome(BigDecimal platIncome) {
        this.platIncome = platIncome;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getPlatCost() {
        return platCost;
    }

    public void setPlatCost(BigDecimal platCost) {
        this.platCost = platCost;
    }

    public BigDecimal getPlatProfit() {
        return platProfit;
    }

    public void setPlatProfit(BigDecimal platProfit) {
        this.platProfit = platProfit;
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(Date paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public Short getRefundTimes() {
        return refundTimes;
    }

    public void setRefundTimes(Short refundTimes) {
        this.refundTimes = refundTimes;
    }

    public BigDecimal getSuccessRefundAmount() {
        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {
        this.successRefundAmount = successRefundAmount;
    }

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
}
