package com.abel.eventbookingservice.services;

import com.abel.eventbookingservice.dto.EventRequestDTO;
import com.abel.eventbookingservice.dto.EventResponseDTO;
import com.abel.eventbookingservice.entities.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventResponseDTO createEvent(EventRequestDTO eventRequestDTO);

    List<EventResponseDTO> getEvents(String name, LocalDateTime startDate, LocalDateTime endDate, String category);

//public List<Employee> findAll();
//
//public Employee findById(int theId);
//
//public void save(Employee theEmployee);
//
//public void deleteById(int theId);
//
//public List<Employee> searchBy(String theName);


}
