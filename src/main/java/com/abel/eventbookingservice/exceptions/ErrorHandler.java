package com.abel.eventbookingservice.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorDetails>
    constraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest req){

        log.info("inside constraintViolationException handler");

        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage(),
                        req.getDescription(true), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorDetails.getCode()).body(errorDetails);

    }

    @ExceptionHandler(DateTimeException.class)
    public final ResponseEntity<ErrorDetails>
    dateTimeExceptionHandler(DateTimeException ex, WebRequest req){

        log.info("inside DateTimeException handler");

        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage(),
                        req.getDescription(true), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorDetails.getCode()).body(errorDetails);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ErrorDetails>
    noSuchElementExceptionHandler(NoSuchElementException ex, WebRequest req){

        log.info("inside NoSuchElementException handler");

        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage() +": "+"invalid event Id:no such event exists",
                        req.getDescription(true), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorDetails.getCode()).body(errorDetails);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.info("inside MethodArgumentNotValidException handler");

        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage(),
                        request.getDescription(true), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(errorDetails.getCode()).body(errorDetails);

    }



}
