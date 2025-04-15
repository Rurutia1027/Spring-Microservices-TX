package com.mini.payment.user.service;

import com.mini.payment.user.entity.UserInfo;
import com.mini.payment.user.entity.UserPayInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserInfoService {
    UserInfo saveData(UserInfo userInfo);
    UserInfo updateData(UserInfo userInfo);
    UserInfo getDataById(Long id);
    Page<UserInfo> listPage(UserInfo param, Pageable pageable);
    UserInfo registerOffline(String username, String mobile, String password);
    UserInfo getDataByMerchantNo(String merchantNo);
    UserInfo getDataByMobile(String mobile);
    List<UserInfo> listAll();
}
