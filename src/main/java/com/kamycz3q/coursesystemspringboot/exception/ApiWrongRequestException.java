package com.kamycz3q.coursesystemspringboot.exception;

public class ApiWrongRequestException extends RuntimeException{
    public ApiWrongRequestException(String message) {
        super(message);
    }
}
