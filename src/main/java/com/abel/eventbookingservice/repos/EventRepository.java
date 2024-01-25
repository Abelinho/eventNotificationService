package com.abel.eventbookingservice.repos;

import com.abel.eventbookingservice.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findByEventNameContainingIgnoreCaseAndEventDateBetweenAndCategory(String name, LocalDateTime startDate, LocalDateTime endDate, String category);

    List<Event> findByEventNameContainingIgnoreCase(String name);

    List<Event> findByEventDateGreaterThanEqualAndEventDateLessThanEqual(LocalDateTime startDate, LocalDateTime endDate);//inclusive

    //List<Event> findByEventDateBetween(LocalDateTime startDate, LocalDateTime endDate);//non-inclusive

    List<Event> findByCategory(String category);
}
