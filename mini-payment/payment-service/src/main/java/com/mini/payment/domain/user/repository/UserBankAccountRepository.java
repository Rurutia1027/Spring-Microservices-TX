package com.mini.payment.domain.user.repository;

import com.mini.payment.repository.BaseRepository;
import com.mini.payment.domain.user.entity.UserBankAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBankAccountRepository extends BaseRepository<UserBankAccount> {

}
