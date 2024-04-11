package com.coaltionbuilder.coalitionbuilder_be.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
