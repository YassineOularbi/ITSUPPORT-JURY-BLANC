package com.itsupport.exception;

/**
 * Exception thrown when an admin entity is not found.
 *
 * This exception indicates that an attempt to retrieve or operate on an admin entity has failed
 * because the specified admin could not be found in the system. It extends RuntimeException
 * and provides a default error message indicating that the admin was not found.
 *
 * Example usage:
 *
 * if (admin == null) {
 *     throw new AdminNotFoundException();
 * }
 *
 * @see com.itsupport.controller.AdminController
 * @see com.itsupport.service.AdminService
 */
public class AdminNotFoundException extends RuntimeException {
    /**
     * Constructs a new AdminNotFoundException with a default message.
     */
    public AdminNotFoundException() {
        super("Admin not found !");
    }
}
