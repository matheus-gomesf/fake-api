package com.example.fakeapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException{
    private HttpStatus status;

    public UserException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public UserException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public UserException() {
        super("User not found");
        this.status = HttpStatus.BAD_REQUEST;
    }
}
