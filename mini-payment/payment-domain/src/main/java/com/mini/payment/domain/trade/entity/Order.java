package com.mini.payment.domain.trade.entity;

import com.mini.payment.domain.DomainImpl;
import com.mini.payment.enums.PublicEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "trade_order")
public class Order extends DomainImpl {
    private String productName;
    private String orderNo;
    private BigDecimal orderAmount;
    private String orderFrom;
    private String merchantName;
    private String merchantNo;
    private Date orderTime;
    private Date orderDate;
    private String orderIp;
    private String orderReferUrl;
    private String orderReturnUrl;
    private String notifyCallbackUrl;
    private String cancelReason;
    private Integer orderValidPeriodInSeconds;
    private String paymentWayCode;
    private String paymentWayType;
    private String description;

    // transaction business type
    private String trxType;
    // transaction number
    private String trxNo;

    private String payTypeCode;
    private String payTypeName;

    private String fundIntoType;
    private String isRefund = PublicEnum.NO.name();
    private int refundTimes;
    private BigDecimal successRefundAmount;

    // -- getters && setters --
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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public String getOrderReturnUrl() {
        return orderReturnUrl;
    }

    public void setOrderReturnUrl(String orderReturnUrl) {
        this.orderReturnUrl = orderReturnUrl;
    }

    public String getNotifyCallbackUrl() {
        return notifyCallbackUrl;
    }

    public void setNotifyCallbackUrl(String notifyCallbackUrl) {
        this.notifyCallbackUrl = notifyCallbackUrl;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getOrderValidPeriodInSeconds() {
        return orderValidPeriodInSeconds;
    }

    public void setOrderValidPeriodInSeconds(Integer orderValidPeriodInSeconds) {
        this.orderValidPeriodInSeconds = orderValidPeriodInSeconds;
    }

    public String getPaymentWayCode() {
        return paymentWayCode;
    }

    public void setPaymentWayCode(String paymentWayCode) {
        this.paymentWayCode = paymentWayCode;
    }

    public String getPaymentWayType() {
        return paymentWayType;
    }

    public void setPaymentWayType(String paymentWayType) {
        this.paymentWayType = paymentWayType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
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

    public String getFundIntoType() {
        return fundIntoType;
    }

    public void setFundIntoType(String fundIntoType) {
        this.fundIntoType = fundIntoType;
    }

    public String getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(String isRefund) {
        this.isRefund = isRefund;
    }

    public int getRefundTimes() {
        return refundTimes;
    }

    public void setRefundTimes(int refundTimes) {
        this.refundTimes = refundTimes;
    }

    public BigDecimal getSuccessRefundAmount() {
        return successRefundAmount;
    }

    public void setSuccessRefundAmount(BigDecimal successRefundAmount) {
        this.successRefundAmount = successRefundAmount;
    }
}