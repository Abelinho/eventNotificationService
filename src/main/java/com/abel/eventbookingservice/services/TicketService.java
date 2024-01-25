package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.TicketRequest;
import com.abel.eventbookingservice.entities.Ticket;

public interface TicketService {

    Long reserveTicket(TicketRequest ticket, Long eventId);
}
