package com.example.musicApp.exception;

public class ConnectionNotFoundException extends RuntimeException{
    public ConnectionNotFoundException(String message) {
        super(message);
    }

}

