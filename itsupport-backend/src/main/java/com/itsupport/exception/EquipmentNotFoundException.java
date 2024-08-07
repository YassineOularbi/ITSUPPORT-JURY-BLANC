package com.itsupport.exception;

/**
 * Exception thrown when an equipment entity is not found.
 *
 * This exception signals that an operation related to equipment could not be completed
 * because the specified equipment was not found in the system. It extends RuntimeException
 * and includes a default error message indicating that the equipment was not found.
 *
 * Example usage:
 *
 * if (equipment == null) {
 *     throw new EquipmentNotFoundException();
 * }
 *
 * @see com.itsupport.controller.AdminController
 * @see com.itsupport.service.EquipmentService
 */
public class EquipmentNotFoundException extends RuntimeException {
    /**
     * Constructs a new EquipmentNotFoundException with a default message.
     */
    public EquipmentNotFoundException() {
        super("Equipment not found !");
    }
}
