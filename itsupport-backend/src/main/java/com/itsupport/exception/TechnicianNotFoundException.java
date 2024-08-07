package com.itsupport.exception;

/**
 * Exception thrown when a technician entity is not found.
 *
 * This exception indicates that an operation related to a technician could not be completed
 * because the specified technician was not found in the system. It extends RuntimeException
 * and includes a default error message indicating that the technician was not found.
 *
 * Example usage:
 *
 * if (technician == null) {
 *     throw new TechnicianNotFoundException();
 * }
 *
 * @see com.itsupport.controller.AdminController
 * @see com.itsupport.service.TechnicianService
 */
public class TechnicianNotFoundException extends RuntimeException {
    /**
     * Constructs a new TechnicianNotFoundException with a default message.
     */
    public TechnicianNotFoundException() {
        super("Technician not found !");
    }
}
