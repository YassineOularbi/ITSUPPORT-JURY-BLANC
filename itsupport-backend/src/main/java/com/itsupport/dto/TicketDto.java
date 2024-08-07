package com.itsupport.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for representing a ticket.
 *
 * This class is used to encapsulate the data associated with a ticket in the system. It is used to transfer
 * ticket-related information between different layers of the application.
 *
 * <p>Example usage:</p>
 * <pre>
 * TicketDto ticketDto = new TicketDto();
 * ticketDto.setDescription("Issue with the network connectivity.");
 * </pre>
 *
 * @see com.itsupport.service.TicketService
 * @see com.itsupport.controller.AdminController
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    /**
     * Description of the ticket.
     * This field provides details or context about the ticket.
     *
     * Example: "Issue with the network connectivity.", "Printer not working"
     */
    private String description;
}
