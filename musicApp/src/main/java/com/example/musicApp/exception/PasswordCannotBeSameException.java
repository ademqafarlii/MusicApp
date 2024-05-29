package com.example.musicApp.exception;

public class PasswordCannotBeSameException extends RuntimeException {
    public PasswordCannotBeSameException(String message) {
        super(message);
    }
}
