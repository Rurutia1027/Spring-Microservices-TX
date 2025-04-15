package com.mini.payment.user.service.impl;

import com.mini.payment.user.service.UserBuildNoService;
import org.springframework.stereotype.Service;

@Service("userBuildNoService")
public class UserBuildNoServiceImpl implements UserBuildNoService {
    @Override
    public String buildUserNo() {
        return "";
    }

    @Override
    public String buildAccountNo() {
        return "";
    }

    @Override
    public String buildTrxNo() {
        return "";
    }

    @Override
    public String buildBankOrderNo() {
        return "";
    }

    @Override
    public String buildReconciliationNo() {
        return "";
    }
}
