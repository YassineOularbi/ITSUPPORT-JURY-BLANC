package com.itsupport.enums;

/**
 * Enumeration representing the various statuses of a ticket.
 *
 * This enum defines the possible statuses that a ticket can have within the system.
 * It is used to track and manage the lifecycle of a ticket, facilitating operations such as
 * updates, reporting, and status transitions.
 *
 * Possible statuses:
 * - PENDING: The ticket has been created but not yet addressed.
 * - PROCESSING: The ticket is currently being worked on.
 * - REPAIRING: The ticket is in the process of being repaired.
 * - REPAIRED: The issue reported in the ticket has been repaired.
 * - FAILED: The ticket has failed to be resolved or the repair was unsuccessful.
 *
 * Example usage:
 *
 * TicketStatus status = TicketStatus.PENDING;
 * if (status == TicketStatus.REPAIRED) {
 *     // Handle repaired ticket
 * }
 *
 * @see com.itsupport.model.Ticket
 * @see com.itsupport.service.TicketService
 */
public enum TicketStatus {
    PENDING,
    PROCESSING,
    REPAIRING,
    REPAIRED,
    FAILED
}
