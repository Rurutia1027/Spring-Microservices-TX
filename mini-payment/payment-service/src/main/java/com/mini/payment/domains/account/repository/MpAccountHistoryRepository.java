package com.mini.payment.domains.account.repository;

import com.mini.payment.domains.account.entity.MpAccountHistory;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MpAccountHistoryRepository extends BaseRepository<MpAccountHistory>,
        JpaSpecificationExecutor<MpAccountHistory> {

}
