package com.mini.payment.domains.account.repository;

import com.mini.payment.domains.account.entity.MpAccount;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MpAccountRepository extends BaseRepository<MpAccount>,
        JpaSpecificationExecutor<MpAccount> {

}
