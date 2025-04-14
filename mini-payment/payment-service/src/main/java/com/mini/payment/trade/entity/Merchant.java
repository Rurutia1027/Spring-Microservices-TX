package com.mini.payment.trade.entity;

import com.mini.payment.domain.DomainImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "trade_merchant")
public class Merchant extends DomainImpl {
    private String businessCode;
    private String merchantNo;
    private String idCardNo;
    private String idCardName;
    private String idCardValidTime;
    private String idCardValidTimeBegin;
    private String idCardValidTimeEnd;
    private String bankName;
    private String bankPostCode;
    private String bankCardNumber;
    private String storeName;
    private String storePostCode;
    private String storeStreet;
    private String storeEntrancePic;
    private String storeInDoorPic;
    private String merchantStoreShortName;
    private String storePhoneNo;
    private String taxRate;
    private String merchantPhoneNo;

    // -- getter && setter --

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getIdCardValidTime() {
        return idCardValidTime;
    }

    public void setIdCardValidTime(String idCardValidTime) {
        this.idCardValidTime = idCardValidTime;
    }

    public String getIdCardValidTimeBegin() {
        return idCardValidTimeBegin;
    }

    public void setIdCardValidTimeBegin(String idCardValidTimeBegin) {
        this.idCardValidTimeBegin = idCardValidTimeBegin;
    }

    public String getIdCardValidTimeEnd() {
        return idCardValidTimeEnd;
    }

    public void setIdCardValidTimeEnd(String idCardValidTimeEnd) {
        this.idCardValidTimeEnd = idCardValidTimeEnd;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankPostCode() {
        return bankPostCode;
    }

    public void setBankPostCode(String bankPostCode) {
        this.bankPostCode = bankPostCode;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePostCode() {
        return storePostCode;
    }

    public void setStorePostCode(String storePostCode) {
        this.storePostCode = storePostCode;
    }

    public String getStoreStreet() {
        return storeStreet;
    }

    public void setStoreStreet(String storeStreet) {
        this.storeStreet = storeStreet;
    }

    public String getStoreEntrancePic() {
        return storeEntrancePic;
    }

    public void setStoreEntrancePic(String storeEntrancePic) {
        this.storeEntrancePic = storeEntrancePic;
    }

    public String getStoreInDoorPic() {
        return storeInDoorPic;
    }

    public void setStoreInDoorPic(String storeInDoorPic) {
        this.storeInDoorPic = storeInDoorPic;
    }

    public String getMerchantStoreShortName() {
        return merchantStoreShortName;
    }

    public void setMerchantStoreShortName(String merchantStoreShortName) {
        this.merchantStoreShortName = merchantStoreShortName;
    }

    public String getStorePhoneNo() {
        return storePhoneNo;
    }

    public void setStorePhoneNo(String storePhoneNo) {
        this.storePhoneNo = storePhoneNo;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getMerchantPhoneNo() {
        return merchantPhoneNo;
    }

    public void setMerchantPhoneNo(String merchantPhoneNo) {
        this.merchantPhoneNo = merchantPhoneNo;
    }
}