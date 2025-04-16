package com.mini.payment.domains.account.repository;

import com.mini.payment.domains.account.entity.MpSettDailyCollect;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MpSettDailyCollectRepository extends BaseRepository<MpSettDailyCollect>,
        JpaSpecificationExecutor<MpSettDailyCollect> {

}
