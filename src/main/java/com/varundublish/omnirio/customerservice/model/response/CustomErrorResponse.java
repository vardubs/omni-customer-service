package com.varundublish.omnirio.customerservice.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CustomErrorResponse {

    private String status;
    private String message;
    private LocalDateTime timestamp;

}
