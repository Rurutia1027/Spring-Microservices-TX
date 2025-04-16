package com.mini.payment.domains.reconciliation.repository;

import com.mini.payment.domains.reconciliation.entity.MpAccountCheckBatch;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MpAccountCheckBatchRepository extends BaseRepository<MpAccountCheckBatch>,
        JpaSpecificationExecutor<MpAccountCheckBatch> {

}
