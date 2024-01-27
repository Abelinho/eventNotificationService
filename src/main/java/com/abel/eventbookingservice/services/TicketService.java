package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.TicketRequest;
import com.abel.eventbookingservice.entities.Ticket;

import java.util.List;

public interface TicketService {

    Long reserveTicket(TicketRequest ticket, Long eventId);

    List<Ticket> getAllTickets();

    Long cancelReservation(Long ticketId);
}
