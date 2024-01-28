package com.abel.eventbookingservice.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.abel.eventbookingservice.dto.TicketRequest;
import com.abel.eventbookingservice.entities.Event;
import com.abel.eventbookingservice.entities.Ticket;
import com.abel.eventbookingservice.exceptions.NoSuchEventException;
import com.abel.eventbookingservice.repos.EventRepository;
import com.abel.eventbookingservice.repos.TicketRepository;

class TicketServiceImplTest {
	
	private EventRepository eventRepository = Mockito.mock(EventRepository.class);
	
	private TicketRepository ticketRepository = Mockito.mock(TicketRepository.class);
	
	private TicketServiceImpl ticketServiceImpl = new TicketServiceImpl(ticketRepository,eventRepository);
	
	private static Long eventId;
	
	private static TicketRequest ticketRequest;
	
	private static Event event;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		// Arrange
        eventId = 1L;
        ticketRequest = new TicketRequest();
        
         event = new Event();
        event.setId(eventId);
		
	}

    @Test
    void reserveTicket_ValidEventId_ReturnsReservedTicketEventId() {       

        
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        Ticket ticket = new Ticket();
        ticket.setEvent(event);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // Act
        Long result = ticketServiceImpl.reserveTicket(ticketRequest, eventId);

        // Assert
        assertNotNull(result);
        assertEquals(eventId, result);

        // Verify
        verify(eventRepository).findById(eventId);
        verify(ticketRepository).save(any(Ticket.class));
        verifyNoMoreInteractions(eventRepository, ticketRepository);
    }
	
	
    @Test
    void reserveTicket_InvalidEventId_ThrowsNoSuchEventException() {
        // Arrange
        Long invalidEventId = 999L;
//        TicketRequest ticketRequest = new TicketRequest();

        when(eventRepository.findById(invalidEventId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> ticketServiceImpl.reserveTicket(ticketRequest, invalidEventId));

        // Verify
        verify(eventRepository).findById(invalidEventId);
        verifyNoInteractions(ticketRepository);
    }
    

	

}
