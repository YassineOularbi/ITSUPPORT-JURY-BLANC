package com.itsupport.auth.exception;

/**
 * Custom exception class for handling login-related errors.
 *
 * This class extends RuntimeException and provides specific error messages
 * for different login failure scenarios such as invalid credentials, user not found,
 * and authentication failure.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
public class LoginException extends RuntimeException {

    /**
     * Constructs a new LoginException with the specified detail message.
     *
     * @param message the detail message.
     */
    public LoginException(String message) {
        super(message);
    }

    /**
     * Returns a LoginException indicating invalid credentials.
     *
     * @return a LoginException with the message "Invalid username or password."
     */
    public static LoginException invalidCredentials() {
        return new LoginException("Invalid username or password.");
    }

    /**
     * Returns a LoginException indicating that the user was not found.
     *
     * @return a LoginException with the message "User not found."
     */
    public static LoginException userNotFound() {
        return new LoginException("User not found.");
    }

    /**
     * Returns a LoginException indicating that the authentication failed.
     *
     * @return a LoginException with the message "Authentication failed. Please try again."
     */
    public static LoginException authenticationFailed() {
        return new LoginException("Authentication failed. Please try again.");
    }
}
