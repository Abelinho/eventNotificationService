package com.abel.eventbookingservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket extends AbstractEntity {

    @Column(name = "attendee_count", nullable = false)
    private int attendeesCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")//,referencedColumnName = "ticket" unique=true?
    private Event event;

    private String attendeeEmail;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public int getAttendeesCount() {
//        return attendeesCount;
//    }
//
//    public void setAttendeesCount(int attendeesCount) {
//        this.attendeesCount = attendeesCount;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
//
//    @Override
//    public String toString() {
//        return "Ticket{" +
//                "attendeesCount=" + attendeesCount +
//                ", event=" + event +
//                '}';
//    }

    //    TicketRequest:
//    type: object
//    required:
//            - attendeesCount
//    properties:
//    attendeesCount:
//    type: integer
//    minimum: 1

}
