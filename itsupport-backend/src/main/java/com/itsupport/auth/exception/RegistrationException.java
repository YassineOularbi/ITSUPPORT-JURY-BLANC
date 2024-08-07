package com.itsupport.auth.exception;

/**
 * Custom exception class for handling registration-related errors.
 *
 * This class extends RuntimeException and is used to indicate issues during
 * user registration, such as when a user with the provided username already exists.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
public class RegistrationException extends RuntimeException {

    /**
     * Constructs a new RegistrationException with a default detail message.
     * The default message is "User with this username already exists."
     */
    public RegistrationException() {
        super("User with this username already exists.");
    }
}
