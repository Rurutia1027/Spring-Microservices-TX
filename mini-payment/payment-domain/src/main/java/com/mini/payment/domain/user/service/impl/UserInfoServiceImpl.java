package com.mini.payment.domain.user.service.impl;

import com.mini.payment.domain.user.entity.UserInfo;
import com.mini.payment.domain.user.service.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public UserInfo saveData(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo updateData(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo getDataById(Long id) {
        return null;
    }

    @Override
    public Page<UserInfo> listPage(UserInfo param, Pageable pageable) {
        return null;
    }

    @Override
    public UserInfo registerOffline(String username, String mobile, String password) {
        return null;
    }

    @Override
    public UserInfo getDataByMerchantNo(String merchantNo) {
        return null;
    }

    @Override
    public UserInfo getDataByMobile(String mobile) {
        return null;
    }

    @Override
    public List<UserInfo> listAll() {
        return List.of();
    }
}
