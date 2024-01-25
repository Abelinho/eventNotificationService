package com.abel.eventbookingservice.repos;

import com.abel.eventbookingservice.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
