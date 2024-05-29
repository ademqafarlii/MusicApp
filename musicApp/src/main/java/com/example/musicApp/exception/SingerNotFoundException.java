package com.example.musicApp.exception;

public class SingerNotFoundException extends RuntimeException {
    public SingerNotFoundException(String message) {
        super(message);
    }

}
