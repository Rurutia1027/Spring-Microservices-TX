package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsAuditLog;
import com.mini.payment.permission.repository.PmsAuditLogRepository;
import com.mini.payment.permission.service.PmsAuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("pmsAuditLogService")
public class PmsAuditLogServiceImpl implements PmsAuditLogService {
    @Autowired
    private PmsAuditLogRepository pmsAuditLogRepository;

    @Override
    public PmsAuditLog saveData(PmsAuditLog pmsAuditLog) {
        return null;
    }

    @Override
    public PmsAuditLog updateData(PmsAuditLog pmsAuditLog) {
        return null;
    }

    @Override
    public PmsAuditLog getById(Long id) {
        return null;
    }

    @Override
    public Page<PmsAuditLog> listPage(PmsAuditLog pmsAuditLog, Pageable pageable) {
        return null;
    }
}
