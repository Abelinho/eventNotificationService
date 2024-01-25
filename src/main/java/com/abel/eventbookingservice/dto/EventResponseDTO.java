package com.abel.eventbookingservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
public class EventResponseDTO {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private String date;

    private Integer availableAttendeesCount;

    private String description;

    private String category;

}
