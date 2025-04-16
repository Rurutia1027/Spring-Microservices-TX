package com.mini.payment.domain.user.service;

import com.mini.payment.domain.user.entity.UserPayWay;
import com.mini.payment.domain.user.exception.PayBizException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPayWayService {
    UserPayWay saveData(UserPayWay data);

    UserPayWay updateData(UserPayWay data);

    UserPayWay getDataById(Long id);

    UserPayWay getByTypeCode(String payProductCode, String payWayCode,
                             String typeCode);

    Page<UserPayWay> listPage(UserPayWay param, Pageable pageable);

    UserPayWay createPayWay(String payProductCode, String payWayCode, String payTypeCode,
                            Double payRate) throws PayBizException;

    List<UserPayWay> listByProductCode(String payProductCode);

    List<UserPayWay> listAll();
}
