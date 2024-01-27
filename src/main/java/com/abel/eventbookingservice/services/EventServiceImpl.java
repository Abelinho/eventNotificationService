package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.EventRequestDTO;
import com.abel.eventbookingservice.dto.EventResponseDTO;
import com.abel.eventbookingservice.entities.Event;
import com.abel.eventbookingservice.repos.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  @Override
  public EventResponseDTO createEvent(EventRequestDTO eventRequestDTO) {

    Event event = new Event();
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    event.setAttendeeCount(eventRequestDTO.getAvailableAttendeesCount());
    event.setCategory(eventRequestDTO.getCategory());
    event.setEventDescription(eventRequestDTO.getEventDescription());
    event.setEventName(eventRequestDTO.getEventName());
    //event.setEventDate(LocalDateTime.parse(eventRequestDTO.getEventDate(),formatter));
    event.setEventDate(eventRequestDTO.getEventDate());

    Event savedEvent = eventRepository.save(event);
    //send notification?...to who?

    //build the response:
    return EventResponseDTO.builder()
            .id(savedEvent.getId())
            .category(savedEvent.getCategory())
            .availableAttendeesCount(savedEvent.getAttendeeCount())
            .description(savedEvent.getEventDescription())
            .name(savedEvent.getEventName())
            .date(savedEvent.getEventDate().toString())
            .build();

  }

  @Override
  public List<EventResponseDTO> getEvents(String name, LocalDateTime startDate, LocalDateTime endDate, String category) {

   //EventResponseDTO eventResponseDTO;

    // Check which parameters are provided and construct a query accordingly
    if (name != null && startDate != null && endDate != null && category != null) {
      // All parameters provided
      List<Event> events = eventRepository.findByEventNameContainingIgnoreCaseAndEventDateBetweenAndCategory(
              name, startDate, endDate, category);
      List<EventResponseDTO> response = new ArrayList<>();


      //what happens when there are no matching events(events is an empty list)?
      //build a responseDTO and add to the response list
      events.forEach(event -> {
        response.add(EventResponseDTO.builder()
                .availableAttendeesCount(event.getAttendeeCount()
                ).build());
      });
      return response; //repeat the same for the else if blocks

    } else if (name != null) {
      // Only name is provided
      System.out.println("Only name is passed");
      return  buildResponse(eventRepository.findByEventNameContainingIgnoreCase(name)) ;
    }
//    else if (startDate != null && endDate != null) {
//      // Date range is provided
//      System.out.println("Only date is passed");
//     // return buildResponse(eventRepository.findByEventDateBetween(startDate, endDate)) ;
//      return buildResponse(eventRepository.findByEventDateGreaterThanEqualAndEventDateLessThanEqual(startDate, endDate)) ;
//    } //the above block
    else if (category != null) {
      // Only category is provided
      System.out.println("Only category is passed");
      return buildResponse(eventRepository.findByCategory(category));
    } else {
      // No parameters provided, return all events
      System.out.println("No parameter is passed");
      return buildResponse(eventRepository.findAll());

    }
  }

   List<EventResponseDTO> buildResponse(List<Event> events){
     List<EventResponseDTO> response = new ArrayList<>();

     //build a responseDTO and add to the response list
     events.forEach(event -> {
       response.add(EventResponseDTO.builder()
               .id(event.getId())
               .name(event.getEventName())
               .description(event.getEventDescription())
               .category(event.getCategory())
               .date(event.getEventDate().toString())
               .availableAttendeesCount(event.getAttendeeCount()
               ).build());
     });
     return response;
   }

}
