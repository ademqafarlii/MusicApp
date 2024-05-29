package com.example.musicApp.exception;

public class FollowNotFoundException extends RuntimeException{

    public FollowNotFoundException(String message) {
        super(message);
    }
}
