package com.urbanek.webApp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityHandler
        extends ResponseEntityExceptionHandler {

    private final String VALIDATION_FAILED = "VALIDATION FAILED";

    private ExceptionResponse createCustomExceptionResponse(String message, String description){
        return new ExceptionResponse(LocalDate.now(), message,
                description);
    }
    private ResponseEntity<Object> createNotFoundException(RuntimeException ex, WebRequest request){
        ExceptionResponse exceptionResponse = createCustomExceptionResponse(ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(EmployeeNotFoundException ex, WebRequest request){
        return createNotFoundException(ex, request);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public final ResponseEntity<Object> handleCompanyNotFoundException(EmployeeNotFoundException ex, WebRequest request){
        return createNotFoundException(ex, request);
    }

    @ExceptionHandler(EmployeeCityNotFound.class)
    public final ResponseEntity<Object> handleEmployeeCityNotFoundException(EmployeeCityNotFound ex, WebRequest request){
        return createNotFoundException(ex,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), VALIDATION_FAILED,
                ex.getBindingResult().getAllErrors().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
