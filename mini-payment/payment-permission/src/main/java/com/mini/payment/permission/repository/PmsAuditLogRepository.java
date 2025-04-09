package com.mini.payment.permission.repository;

import com.mini.payment.permission.entity.PmsAuditLog;
import com.mini.payment.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PmsAuditLogRepository extends BaseRepository<PmsAuditLog>,
        JpaSpecificationExecutor<PmsAuditLog> {

}
