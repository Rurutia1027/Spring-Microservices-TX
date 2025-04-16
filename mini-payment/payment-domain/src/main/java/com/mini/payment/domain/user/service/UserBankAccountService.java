package com.mini.payment.domain.user.service;

import com.mini.payment.domain.user.entity.UserBankAccount;

public interface UserBankAccountService {
    UserBankAccount saveData(UserBankAccount data);

    UserBankAccount updateData(UserBankAccount data);

    UserBankAccount getByUserNo(String userNo);

    UserBankAccount createOrUpdate(UserBankAccount data);

}
