package com.mini.payment.user.service.impl;

import com.mini.payment.user.entity.UserPayInfo;
import com.mini.payment.user.service.UserPayInfoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service("userPayInfoService")
public class UserPayInfoServiceImpl implements UserPayInfoService {
    @Override
    public UserPayInfo saveData(UserPayInfo data) {
        return null;
    }

    @Override
    public UserPayInfo updateData(UserPayInfo data) {
        return null;
    }

    @Override
    public UserPayInfo getDataById(Long id) {
        return null;
    }

    @Override
    public Page<UserPayInfo> listPage(UserPayInfo param, Pageable pageable) {
        return null;
    }

    @Override
    public UserPayInfo getByUserNoAndPayWayCode(String userNo, String payWayCode) {
        return null;
    }
}
