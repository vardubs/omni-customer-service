package com.varundublish.omnirio.customerservice.exceptions;

import com.varundublish.omnirio.customerservice.model.response.CustomErrorResponse;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Log
public class CustomerServiceControllerAdvice {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceControllerAdvice.class);


    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<CustomErrorResponse> handleBadRequest(final DataIntegrityViolationException e) {
        LOGGER.error(e.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(e.getLocalizedMessage());
        customErrorResponse.setStatus("400");
        customErrorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<CustomErrorResponse> handleConstraintViolationException(final ConstraintViolationException e) {
        LOGGER.error(e.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage("Bad Request");
        customErrorResponse.setStatus("400");
        customErrorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedException(AccessDeniedException e) {

        LOGGER.error(e.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(e.getMessage());
        customErrorResponse.setStatus("403");
        customErrorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(customErrorResponse, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> handleException(Exception e) {

        LOGGER.error(e.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(e.getLocalizedMessage());
        customErrorResponse.setStatus("500");
        customErrorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {

        LOGGER.error(e.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(e.getLocalizedMessage());
        customErrorResponse.setStatus("404");
        customErrorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(final IllegalArgumentException e) {
        LOGGER.error(e.getMessage());
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();

        customErrorResponse.setMessage(e.getLocalizedMessage());
        customErrorResponse.setStatus("404");
        customErrorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
}

