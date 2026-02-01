package com.billiards.service;

import com.billiards.domain.AuditLog;
import com.billiards.repository.AuditLogRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public AuditLog create(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }
}
