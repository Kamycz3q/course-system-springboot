package com.kamycz3q.coursesystemspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<String> handleNotFoundExcepiton(ApiNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ApiWrongRequestException.class)
    public ResponseEntity<String> handleWrongRequestException(ApiWrongRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }
}
