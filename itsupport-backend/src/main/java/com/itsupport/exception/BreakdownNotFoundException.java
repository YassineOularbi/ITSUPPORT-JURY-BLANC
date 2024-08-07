package com.itsupport.exception;

/**
 * Exception thrown when a breakdown entity is not found.
 *
 * This exception indicates that an attempt to retrieve or operate on a breakdown entity has failed
 * because the specified breakdown could not be found in the system. It extends RuntimeException
 * and provides a default error message indicating that the breakdown was not found.
 *
 * Example usage:
 *
 * if (breakdown == null) {
 *     throw new BreakdownNotFoundException();
 * }
 *
 * @see com.itsupport.controller.AdminController
 * @see com.itsupport.service.BreakdownService
 */
public class BreakdownNotFoundException extends RuntimeException {
    /**
     * Constructs a new BreakdownNotFoundException with a default message.
     */
    public BreakdownNotFoundException() {
        super("Breakdown not found !");
    }
}
