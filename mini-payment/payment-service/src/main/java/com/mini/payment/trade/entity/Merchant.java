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
}
