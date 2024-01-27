package com.abel.eventbookingservice.repos;

import com.abel.eventbookingservice.entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditLog,Long> {
}
