package com.mini.payment.user.service.impl;

import com.mini.payment.user.entity.UserPayWay;
import com.mini.payment.user.exception.PayBizException;
import com.mini.payment.user.service.UserPayWayService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userPayWayService")
public class UserPayWayServiceImpl implements UserPayWayService {
    @Override
    public UserPayWay saveData(UserPayWay data) {
        return null;
    }

    @Override
    public UserPayWay updateData(UserPayWay data) {
        return null;
    }

    @Override
    public UserPayWay getDataById(Long id) {
        return null;
    }

    @Override
    public UserPayWay getByTypeCode(String payProductCode, String payWayCode, String typeCode) {
        return null;
    }

    @Override
    public Page<UserPayWay> listPage(UserPayWay param, Pageable pageable) {
        return null;
    }

    @Override
    public UserPayWay createPayWay(String payProductCode, String payWayCode, String payTypeCode, Double payRate) throws PayBizException {
        return null;
    }

    @Override
    public List<UserPayWay> listByProductCode(String payProductCode) {
        return List.of();
    }

    @Override
    public List<UserPayWay> listAll() {
        return List.of();
    }
}
