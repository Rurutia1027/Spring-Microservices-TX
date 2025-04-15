package com.mini.payment.user.service;

import com.mini.payment.user.entity.UserBankAccount;

public interface UserBankAccountService {
    UserBankAccount saveData(UserBankAccount data);

    UserBankAccount updateData(UserBankAccount data);

    UserBankAccount getByUserNo(String userNo);

    UserBankAccount createOrUpdate(UserBankAccount data);

}
