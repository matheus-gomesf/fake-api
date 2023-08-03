package com.example.fakeapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RoleException extends RuntimeException{
    private HttpStatus status;

    public RoleException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public RoleException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public RoleException() {
        super("Role not found");
        this.status = HttpStatus.BAD_REQUEST;
    }
}
