package com.abel.eventbookingservice.dto;

import com.abel.eventbookingservice.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventRequestDTO {

    @NotEmpty(message = "eventName field is required")
    //@Max(value = 100)
    private String eventName;

    @NotNull(message = "event date is mandatory")
    @Future(message = "date has to be a future date")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime eventDate;
   // private String eventDate;

    @NotNull(message = "attendeeCount field is required")
    @Min(value = 1)
    @Max(value = 1000)
    private Integer availableAttendeesCount;

    @NotEmpty(message = "describe the event please!!")
    @Length(max = 500,message = "event description cannot be more than 500")
    private String eventDescription;

    @NotEmpty(message = "category field is required")
    private String category;

}
