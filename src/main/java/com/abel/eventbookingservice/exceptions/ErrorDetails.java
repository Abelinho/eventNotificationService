package com.abel.eventbookingservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails implements Serializable {

    private Date timestamp;
    private String message;
    private String details;
    private HttpStatus code;

}
