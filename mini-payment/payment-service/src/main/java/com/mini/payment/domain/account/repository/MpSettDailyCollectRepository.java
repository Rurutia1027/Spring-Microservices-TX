package com.mini.payment.domain.account.repository;

import com.mini.payment.domain.account.entity.MpSettDailyCollect;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MpSettDailyCollectRepository extends BaseRepository<MpSettDailyCollect>,
        JpaSpecificationExecutor<MpSettDailyCollect> {

}
