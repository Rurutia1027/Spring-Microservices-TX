package com.mini.payment.domain.user.service;

import com.mini.payment.domain.user.entity.UserPayProduct;
import com.mini.payment.domain.user.exception.PayBizException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPayProductService {
    UserPayProduct saveData(UserPayProduct data);

    UserPayProduct updateData(UserPayProduct data);

    UserPayProduct getDataById(Long id);

    Page<UserPayProduct> listPage(UserPayProduct param, Pageable pageable);

    UserPayProduct getByProductCode(String productCode, String auditStatus);

    UserPayProduct createPayProduct(String productCode, String productName);

    void deletePayProduct(String productCode) throws PayBizException;

    List<UserPayProduct> listAll();

    void audit(String productCode, String auditStatus) throws PayBizException;
}
