package com.itsupport.exception;

/**
 * Exception thrown when a ticket entity is not found.
 *
 * This exception signals that an operation related to a ticket could not be performed
 * because the specified ticket was not found in the system. It extends RuntimeException
 * and includes a default error message indicating that the ticket was not found.
 *
 * Example usage:
 *
 * if (ticket == null) {
 *     throw new TicketNotFoundException();
 * }
 *
 * @see com.itsupport.controller.AdminController
 * @see com.itsupport.service.TicketService
 */
public class TicketNotFoundException extends RuntimeException {
    /**
     * Constructs a new TicketNotFoundException with a default message.
     */
    public TicketNotFoundException() {
        super("Ticket not found !");
    }
}
