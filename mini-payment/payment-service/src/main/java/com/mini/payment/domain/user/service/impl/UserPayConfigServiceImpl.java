package com.mini.payment.domain.user.service.impl;

import com.mini.payment.domain.user.entity.UserPayConfig;
import com.mini.payment.domain.user.exception.PayBizException;
import com.mini.payment.domain.user.service.UserPayConfigService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userPayConfigService")
public class UserPayConfigServiceImpl implements UserPayConfigService {
    @Override
    public UserPayConfig saveData(UserPayConfig data) {
        return null;
    }

    @Override
    public UserPayConfig updateData(UserPayConfig data) {
        return null;
    }

    @Override
    public UserPayConfig getDataById(Long id) {
        return null;
    }

    @Override
    public Page<UserPayConfig> listPage(UserPayConfig param, Pageable pageable) {
        return null;
    }

    @Override
    public UserPayConfig getByUserNo(String userNo) {
        return null;
    }

    @Override
    public UserPayConfig getByUserNoAndAuditStatus(String userNo, String auditStatus) {
        return null;
    }

    @Override
    public List<UserPayConfig> listByProductCodeAndAuditStatus(String productCode, String auditStatus) {
        return List.of();
    }

    @Override
    public UserPayConfig createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey) throws PayBizException {
        return null;
    }

    @Override
    public UserPayConfig createUserPayConfig(String userNo, String userName, String productCode, String productName, Integer riskDay, String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating, String merchantServerIp) throws PayBizException {
        return null;
    }

    @Override
    public void deleteByUserNo(String userNo) {

    }

    @Override
    public UserPayConfig updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey) throws PayBizException {
        return null;
    }

    @Override
    public UserPayConfig updateUserPayConfig(String userNo, String productCode, String productName, Integer riskDay, String fundIntoType, String isAutoSett, String appId, String merchantId, String partnerKey, String ali_partner, String ali_sellerId, String ali_key, String ali_appid, String ali_rsaPrivateKey, String ali_rsaPublicKey, String securityRating, String merchantServerIp) throws PayBizException {
        return null;
    }

    @Override
    public void audit(String userNo, String auditStatus) {

    }

    @Override
    public UserPayConfig getByPayKey(String payKey) {
        return null;
    }
}
