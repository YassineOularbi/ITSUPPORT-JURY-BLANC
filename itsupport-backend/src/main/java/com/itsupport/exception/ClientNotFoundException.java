package com.itsupport.exception;

/**
 * Exception thrown when a client entity is not found.
 *
 * This exception indicates that an attempt to retrieve or operate on a client entity has failed
 * because the specified client could not be found in the system. It extends RuntimeException
 * and provides a default error message indicating that the client was not found.
 *
 * Example usage:
 *
 * if (client == null) {
 *     throw new ClientNotFoundException();
 * }
 *
 * @see com.itsupport.service.ClientService
 */
public class ClientNotFoundException extends RuntimeException {
    /**
     * Constructs a new ClientNotFoundException with a default message.
     */
    public ClientNotFoundException() {
        super("Client not found !");
    }
}
