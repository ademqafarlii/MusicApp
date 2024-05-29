package com.example.musicApp.exception;

public class PlayListNotFoundException extends RuntimeException {
    public PlayListNotFoundException(String message) {
        super(message);
    }
}
