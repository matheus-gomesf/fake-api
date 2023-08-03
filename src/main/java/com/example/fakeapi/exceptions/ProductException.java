package com.example.fakeapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductException extends RuntimeException{
    private HttpStatus status;

    public ProductException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public ProductException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
