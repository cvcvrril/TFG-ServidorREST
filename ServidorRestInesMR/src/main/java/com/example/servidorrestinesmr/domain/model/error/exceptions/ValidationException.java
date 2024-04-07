package com.example.servidorrestinesmr.domain.model.error.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
