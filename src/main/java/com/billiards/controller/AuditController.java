package com.billiards.controller;

import com.billiards.domain.AuditLog;
import com.billiards.service.AuditService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit")
public class AuditController {
    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping
    public AuditLog create(@RequestBody AuditLog auditLog) {
        return auditService.create(auditLog);
    }

    @GetMapping
    public List<AuditLog> list() {
        return auditService.findAll();
    }
}
