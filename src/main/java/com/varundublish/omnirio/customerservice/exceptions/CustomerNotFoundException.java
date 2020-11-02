package com.varundublish.omnirio.customerservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    private final String id;

    public CustomerNotFoundException(final String id) {
        super("Customer could not be found with id: " + id);
        this.id = id;
    }
}