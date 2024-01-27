package com.abel.eventbookingservice.exceptions;

import org.springframework.http.HttpStatus;

public class EventAPIException extends RuntimeException {

    public EventAPIException(HttpStatus badRequest, String s) {
    }
}
