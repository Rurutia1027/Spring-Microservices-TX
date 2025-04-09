package com.mini.payment.permission.service;

import com.mini.payment.permission.entity.PmsAuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PmsAuditLogService {
    PmsAuditLog saveData(PmsAuditLog pmsAuditLog);

    PmsAuditLog updateData(PmsAuditLog pmsAuditLog);

    PmsAuditLog getById(Long id);

    Page<PmsAuditLog> listPage(PmsAuditLog pmsAuditLog, Pageable pageable);
}
