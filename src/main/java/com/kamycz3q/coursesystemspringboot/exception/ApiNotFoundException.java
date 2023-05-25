package com.kamycz3q.coursesystemspringboot.exception;

public class ApiNotFoundException extends RuntimeException{
    public ApiNotFoundException(String message) {
        super(message);
    }
}
