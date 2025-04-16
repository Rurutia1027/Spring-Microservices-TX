package com.mini.payment.domain.user.service;

import com.mini.payment.domain.user.entity.UserPayInfo;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface UserPayInfoService {
    UserPayInfo saveData(UserPayInfo data);
    UserPayInfo updateData(UserPayInfo data);
    UserPayInfo getDataById(Long id);
    Page<UserPayInfo> listPage(UserPayInfo param, Pageable pageable);
    UserPayInfo getByUserNoAndPayWayCode(String userNo, String payWayCode);
}
