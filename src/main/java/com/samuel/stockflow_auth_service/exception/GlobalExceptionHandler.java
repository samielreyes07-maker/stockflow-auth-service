package com.samuel.stockflow_auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFound(
            ResourceNotFoundException exception
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handlerResourceAlreadyExistException(
            ResourceAlreadyExistsException exception
    ){
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                .body(exception.getMessage());
    }
}
