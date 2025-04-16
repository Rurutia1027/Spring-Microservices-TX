package com.mini.payment.domains.user.service;

import com.mini.payment.domains.user.entity.UserPayConfig;
import com.mini.payment.domains.user.exception.PayBizException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPayConfigService {
    UserPayConfig saveData(UserPayConfig data);

    UserPayConfig updateData(UserPayConfig data);

    UserPayConfig getDataById(Long id);

    Page<UserPayConfig> listPage(UserPayConfig param, Pageable pageable);

    UserPayConfig getByUserNo(String userNo);

    UserPayConfig getByUserNoAndAuditStatus(String userNo, String auditStatus);

    List<UserPayConfig> listByProductCodeAndAuditStatus(String productCode,
                                                        String auditStatus);

    UserPayConfig createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType,
                                      String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                                      String ali_appid, String ali_rsaPrivateKey,
                                      String ali_rsaPublicKey) throws PayBizException;

    UserPayConfig createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType,
                                      String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                                      String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating, String merchantServerIp) throws PayBizException;

    void deleteByUserNo(String userNo);

    UserPayConfig updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                                      String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                                      String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey) throws PayBizException;

    UserPayConfig updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType,
                                      String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key,
                                      String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating, String merchantServerIp) throws PayBizException;

    void audit(String userNo, String auditStatus);

    UserPayConfig getByPayKey(String payKey);
}
