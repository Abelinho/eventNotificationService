package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.TicketRequest;
import com.abel.eventbookingservice.entities.Event;
import com.abel.eventbookingservice.entities.Ticket;
import com.abel.eventbookingservice.exceptions.EventAPIException;
import com.abel.eventbookingservice.exceptions.NoSuchEventException;
import com.abel.eventbookingservice.repos.EventRepository;
import com.abel.eventbookingservice.repos.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author abel.agbachi
 *
 * **/
@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    @Override
    public Long reserveTicket(TicketRequest ticketRequest, Long eventId) {

        //get event has the ticket
        //set it in the ticket and save the ticket
       Optional<Event> optionalEvent = eventRepository.findById(eventId);
       log.info("id of event: " +optionalEvent.get().getId());
      Event event = optionalEvent.orElseThrow(()->
               new NoSuchEventException("no event with id: "+eventId));//new NoSuchEventException("no event with id: "+eventId)

        log.info("id of event returned by optionalOrElsethrow: " +event.getId());

        Ticket ticket = new Ticket();
        ticket.setEvent(event);

        Ticket reservedTicket = ticketRepository.save(ticket);

        return reservedTicket.getEvent().getId();
    }

    @Override
    public List<Ticket> getAllTickets() {

        return ticketRepository.findAll();


    }

    @Override
    public Long cancelReservation(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()->
                new EventAPIException(HttpStatus.NOT_FOUND,"no reservation found with id: "+ticketId));

        Long reservedTicketId = ticket.getId();
        return reservedTicketId;// .deleteById(ticketId);
    }
}
