package com.mini.payment.trade.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "trade_payment")
public class Payment extends DomainImpl {
    private String productName;
    private String orderNo;
    private String trxNo;
    private String bankOrderNo;
    private String bankTrxNo;
    private String merchantName;
    private String merchantNo;
    private String payerUserNo;
    private String payerUserName;
    private BigDecimal payerPayAmount;
    private BigDecimal payerPayFee;
    private String payerAccountType;
    private String receiverUserNo;
    private String receiverName;
    private BigDecimal receiverPayAmount;
    private BigDecimal receiverPayFee;
    private String receiverAccountType;
    private String orderIp;
    private String orderReferUrl;
    private BigDecimal orderAmount;
    private BigDecimal platformIncome = BigDecimal.ZERO;
    private BigDecimal feeRate = BigDecimal.ZERO;
    private BigDecimal platformCost = BigDecimal.ZERO;
    private BigDecimal platformProfit = BigDecimal.ZERO;
    private String returnUrl;
    private String notifyCallbackUrl;
    private String paymentWayCode;
    private String paymentWayName;
    private Date paymentSuccessTime;
    private Date paymentCompleteTime;
    private Boolean isRefund;
    private Integer refundTimes;
    private BigDecimal successRefundAmount;
    private String trxType;
    private String orderFrom;
    private String payTypeCode;
    private String payTypeName;
    private String fundInComeType;
    private String description;
    private String bankReturnMsg;

    // --- getter && setter --

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public String getPayerUserNo() {
        return payerUserNo;
    }

    public void setPayerUserNo(String payerUserNo) {
        this.payerUserNo = payerUserNo;
    }

    public String getPayerUserName() {
        return payerUserName;
    }

    public void setPayerUserName(String payerUserName) {
        this.payerUserName = payerUserName;
    }

    public BigDecimal getPayerPayAmount() {
        return payerPayAmount;
    }

    public void setPayerPayAmount(BigDecimal payerPayAmount) {
        this.payerPayAmount = payerPayAmount;
    }

    public BigDecimal getPayerPayFee() {
        return payerPayFee;
    }

    public void setPayerPayFee(BigDecimal payerPayFee) {
        this.payerPayFee = payerPayFee;
    }

    public String getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(String payerAccountType) {
        this.payerAccountType = payerAccountType;
    }

    public String getReceiverUserNo() {
        return receiverUserNo;
    }

    public void setReceiverUserNo(String receiverUserNo) {
        this.receiverUserNo = receiverUserNo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getReceiverPayAmount() {
        return receiverPayAmount;
    }

    public void setReceiverPayAmount(BigDecimal receiverPayAmount) {
        this.receiverPayAmount = receiverPayAmount;
    }

    public BigDecimal getReceiverPayFee() {
        return receiverPayFee;
    }

    public void setReceiverPayFee(BigDecimal receiverPayFee) {
        this.receiverPayFee = receiverPayFee;
    }

    public String getReceiverAccountType() {
        return receiverAccountType;
    }

    public void setReceiverAccountType(String receiverAccountType) {
        this.receiverAccountType = receiverAccountType;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public String getOrderReferUrl() {
        return orderReferUrl;
    }

    public void setOrderReferUrl(String orderReferUrl) {
        this.orderReferUrl = orderReferUrl;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPlatformIncome() {
        return platformIncome;
    }

    public void setPlatformIncome(BigDecimal platformIncome) {
        this.platformIncome = platformIncome;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getPlatformCost() {
        return platformCost;
    }

    public void setPlatformCost(BigDecimal platformCost) {
        this.platformCost = platformCost;
    }

    public BigDecimal getPlatformProfit() {
        return platformProfit;
    }

    public void setPlatformProfit(BigDecimal platformProfit) {
        this.platformProfit = platformProfit;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyCallbackUrl() {
        return notifyCallbackUrl;
    }

    public void setNotifyCallbackUrl(String notifyCallbackUrl) {
        this.notifyCallbackUrl = notifyCallbackUrl;
    }

    public String getPaymentWayCode() {
        return paymentWayCode;
    }

    public void setPaymentWayCode(String paymentWayCode) {
        this.paymentWayCode = paymentWayCode;
    }

    public String getPaymentWayName() {
        return paymentWayName;
    }

    public void setPaymentWayName(String paymentWayName) {
        this.paymentWayName = paymentWayName;
    }

    public Date getPaymentSuccessTime() {
        return paymentSuccessTime;
    }

    public void setPaymentSuccessTime(Date paymentSuccessTime) {
        this.paymentSuccessTime = paymentSuccessTime;
    }

    public Date getPaymentCompleteTime() {
        return paymentCompleteTime;
    }

    public void setPaymentCompleteTime(Date paymentCompleteTime) {
        this.paymentCompleteTime = paymentCompleteTime;
    }

    public Boolean getRefund() {
        return isRefund;
    }

    public void setRefund(Boolean refund) {
        isRefund = refund;
    }

    public Integer getRefundTimes() {
        return refundTimes;
    }

    public void setRefundTimes(Integer refundTimes) {
        this.refundTimes = refundTimes;
    }

    public BigDecimal getSuccessRefundAmount() {
        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {
        this.successRefundAmount = successRefundAmount;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getFundInComeType() {
        return fundInComeType;
    }

    public void setFundInComeType(String fundInComeType) {
        this.fundInComeType = fundInComeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBankReturnMsg() {
        return bankReturnMsg;
    }

    public void setBankReturnMsg(String bankReturnMsg) {
        this.bankReturnMsg = bankReturnMsg;
    }
}
