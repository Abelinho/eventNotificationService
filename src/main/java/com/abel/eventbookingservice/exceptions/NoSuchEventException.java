package com.abel.eventbookingservice.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchEventException extends RuntimeException{

    public NoSuchEventException(String message){
        super(message);
    }
}
