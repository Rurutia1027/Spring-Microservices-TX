package com.mini.payment.domain.reconciliation.repository;

import com.mini.payment.domain.reconciliation.entity.MpAccountDiscrepancyCheckScratchPool;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MpAccountDiscrepancyCheckScratchPoolRepository extends BaseRepository<MpAccountDiscrepancyCheckScratchPool>,
        JpaSpecificationExecutor<MpAccountDiscrepancyCheckScratchPool> {

}
