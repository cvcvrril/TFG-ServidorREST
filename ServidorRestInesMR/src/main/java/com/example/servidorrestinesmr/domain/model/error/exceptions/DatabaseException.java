package com.example.servidorrestinesmr.domain.model.error.exceptions;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
