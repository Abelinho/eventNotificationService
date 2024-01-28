package com.abel.eventbookingservice.services;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.abel.eventbookingservice.dto.EventRequestDTO;
import com.abel.eventbookingservice.dto.EventResponseDTO;
import com.abel.eventbookingservice.entities.Event;
import com.abel.eventbookingservice.repos.EventRepository;

class EventServiceImplTest {
	
	
    private EventRepository eventRepository = Mockito.mock(EventRepository.class);
    
    private EventServiceImpl eventServiceImpl = new EventServiceImpl(eventRepository);
    
    private static EventRequestDTO eventRequestDTO;
    
    private static Event event;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {	

		 eventRequestDTO = EventRequestDTO.builder()
		.availableAttendeesCount(5)
		.category("Music")
		.eventDate(LocalDateTime.of(2024, 01, 28, 18, 00))
		.eventName("testEvent")		
		.build();	
		
		 event = Event.builder()
		.attendeeCount(eventRequestDTO.getAvailableAttendeesCount())
		.category(eventRequestDTO.getCategory())
		.eventDate(eventRequestDTO.getEventDate())
		.eventName(eventRequestDTO.getEventName())
		.build();
		
	}

	@Test
	void testCreateEvent() {
		
		when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Act
        EventResponseDTO result = eventServiceImpl.createEvent(eventRequestDTO);

        // Assert
        assertNotNull(result);       
        assertEquals("Music", result.getCategory());
        assertEquals(5, result.getAvailableAttendeesCount());       
        assertEquals("testEvent", result.getName());
        assertEquals("2024-01-28T18:00", result.getDate()); // Adjust the date format based on your needs

        // Verify
        verify(eventRepository).save(any(Event.class));
        verifyNoMoreInteractions(eventRepository);
    }
		
	@Test
    void getEvents_AllParametersProvided_ReturnsEventResponseDTOList() {
        // Arrange
        String name = "EventName";
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 28, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 29, 12, 0);
        String category = "Music";

        List<Event> events = new ArrayList<>();
        
        events.add(event);

        when(eventRepository.findByEventNameContainingIgnoreCaseAndEventDateBetweenAndCategory(name, startDate, endDate, category)).thenReturn(events);

        // Act
        List<EventResponseDTO> result = eventServiceImpl.getEvents(name, startDate, endDate, category);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getAvailableAttendeesCount());
       

        // Verify
        verify(eventRepository).findByEventNameContainingIgnoreCaseAndEventDateBetweenAndCategory(name, startDate, endDate, category);
        verifyNoMoreInteractions(eventRepository);
    }

	@Test
    void getEvents_OnlyNameProvided_ReturnsEventResponseDTOList() {
        // Arrange
        String name = "EventName";
        List<Event> events = new ArrayList<>();
        
        events.add(event);

        when(eventRepository.findByEventNameContainingIgnoreCase(name)).thenReturn(events);

        // Act
        List<EventResponseDTO> result = eventServiceImpl.getEvents(name, null, null, null);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getAvailableAttendeesCount());
        

        // Verify
        verify(eventRepository).findByEventNameContainingIgnoreCase(name);
        verifyNoMoreInteractions(eventRepository);
    }

    // Repeat similar tests for other scenarios (e.g., date range, category, no parameters)


}
