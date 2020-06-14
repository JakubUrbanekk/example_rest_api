package com.urbanek.webApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeCityNotFound extends RuntimeException {
    public EmployeeCityNotFound(String s) {
        super(s);
    }
}
