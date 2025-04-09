package com.mini.payment.permission.service.impl;

import com.mini.payment.permission.entity.PmsAuditLog;
import com.mini.payment.permission.repository.PmsAuditLogRepository;
import com.mini.payment.permission.service.PmsAuditLogService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("pmsAuditLogService")
public class PmsAuditLogServiceImpl implements PmsAuditLogService {
    @Autowired
    private PmsAuditLogRepository pmsAuditLogRepository;

    @Override
    public PmsAuditLog saveData(PmsAuditLog pmsAuditLog) {
        return pmsAuditLogRepository.save(pmsAuditLog);
    }

    @Override
    public PmsAuditLog updateData(PmsAuditLog pmsAuditLog) {
        return pmsAuditLogRepository.save(pmsAuditLog);
    }

    @Override
    public PmsAuditLog getById(Long id) {
        return pmsAuditLogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<PmsAuditLog> listPage(PmsAuditLog param, Pageable pageable) {
        return pmsAuditLogRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(param.getIp())) {
                predicates.add(cb.equal(root.get("ip"),
                        param.getIp()));
            }

            if (StringUtils.hasText(param.getOpType())) {
                predicates.add(cb.equal(root.get("opType"),
                        param.getOpType()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }
}
