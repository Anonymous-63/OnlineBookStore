package com.anonymous63.onlinebookstore.services.impl;

import com.anonymous63.onlinebookstore.models.AuditLog;
import com.anonymous63.onlinebookstore.repositories.AuditLogRepo;
import com.anonymous63.onlinebookstore.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogRepo auditRepo;

    @Override
    public void log(String action, String entityName, Long entityId, String username, Long userId, String details) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.setEntityName(entityName);
        auditLog.setEntityId(entityId);
        auditLog.setUsername(username);
        auditLog.setUserId(userId);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLog.setDetails(details);
        this.auditRepo.save(auditLog);
    }
}

