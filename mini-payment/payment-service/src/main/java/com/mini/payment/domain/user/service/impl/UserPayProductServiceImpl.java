package com.mini.payment.domain.user.service.impl;

import com.mini.payment.domain.user.entity.UserPayProduct;
import com.mini.payment.domain.user.exception.PayBizException;
import com.mini.payment.domain.user.service.UserPayProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userPayProductService")
public class UserPayProductServiceImpl implements UserPayProductService {
    @Override
    public UserPayProduct saveData(UserPayProduct data) {
        return null;
    }

    @Override
    public UserPayProduct updateData(UserPayProduct data) {
        return null;
    }

    @Override
    public UserPayProduct getDataById(Long id) {
        return null;
    }

    @Override
    public Page<UserPayProduct> listPage(UserPayProduct param, Pageable pageable) {
        return null;
    }

    @Override
    public UserPayProduct getByProductCode(String productCode, String auditStatus) {
        return null;
    }

    @Override
    public UserPayProduct createPayProduct(String productCode, String productName) {
        return null;
    }

    @Override
    public void deletePayProduct(String productCode) throws PayBizException {

    }

    @Override
    public List<UserPayProduct> listAll() {
        return List.of();
    }

    @Override
    public void audit(String productCode, String auditStatus) throws PayBizException {

    }
}
