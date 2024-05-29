package com.example.musicApp.exception;

public class RatingNotFoundException extends RuntimeException {

    public RatingNotFoundException(String message) {
        super(message);
    }
}
