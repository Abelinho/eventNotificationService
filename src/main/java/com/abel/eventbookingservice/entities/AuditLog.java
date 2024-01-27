package com.abel.eventbookingservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ReservationActivityLog")
@Getter
@Setter
public class AuditLog extends AbstractEntity{

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EVENT_DATE", nullable = false, updatable = false)
    private Date eventDate = new Date();

    @Column(name = "EVENT_ID", nullable = false)
    private Long eventId;

}
