package com.itsupport.auth.exception;

public class RegistrationException extends RuntimeException {
    public RegistrationException() {
        super("User with this username already exists.");
    }
}
