package com.mini.payment.domain.account.repository;

import com.mini.payment.domain.account.entity.MpAccount;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MpAccountRepository extends BaseRepository<MpAccount>,
        JpaSpecificationExecutor<MpAccount> {

}
