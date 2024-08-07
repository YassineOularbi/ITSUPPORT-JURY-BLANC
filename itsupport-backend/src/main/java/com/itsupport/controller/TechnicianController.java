package com.itsupport.controller;

import com.itsupport.exception.*;
import com.itsupport.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

/**
 * Controller for handling technician-related requests.
 *
 * This controller provides endpoints for retrieving processing tickets,
 * all tickets for a technician, and updating ticket statuses.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@RestController
@RequestMapping("/api/technician")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TechnicianController {

    private final TicketService ticketService;

    /**
     * Endpoint for retrieving processing tickets for a technician.
     *
     * @param id the ID of the technician.
     * @return a list of processing tickets or an error message.
     */
    @GetMapping("/get-processing-tickets/{id}")
    @ApiOperation(value = "Get processing tickets by technician ID", notes = "Retrieves all tickets currently being processed by a specific technician.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of processing tickets retrieved successfully."),
            @ApiResponse(code = 404, message = "Tickets not found.")
    })
    public ResponseEntity<?> getProcessingTickets(
            @ApiParam(value = "ID of the technician", required = true) @PathVariable("id") String id) {
        try {
            var tickets = ticketService.getProcessingTickets(Long.valueOf(id));
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all tickets assigned to a technician.
     *
     * @param id the ID of the technician.
     * @return a list of all tickets assigned to the technician or an error message.
     */
    @GetMapping("/get-all-tickets/{id}")
    @ApiOperation(value = "Get all tickets by technician ID", notes = "Retrieves all tickets assigned to a specific technician.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all tickets retrieved successfully."),
            @ApiResponse(code = 404, message = "Tickets not found.")
    })
    public ResponseEntity<?> getAllTickets(
            @ApiParam(value = "ID of the technician", required = true) @PathVariable("id") String id) {
        try {
            var tickets = ticketService.getTicketsByTechnician(Long.valueOf(id));
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating the status of a ticket to "repairing".
     *
     * @param id the ID of the ticket.
     * @return the updated ticket or an error message.
     */
    @PutMapping("/repairing-ticket/{id}")
    @ApiOperation(value = "Update ticket status to 'repairing'", notes = "Updates the status of a specific ticket to 'repairing'.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ticket status updated to 'repairing' successfully."),
            @ApiResponse(code = 404, message = "Ticket not found.")
    })
    public ResponseEntity<?> repairingTicket(
            @ApiParam(value = "ID of the ticket", required = true) @PathVariable("id") String id) {
        try {
            var ticket = ticketService.repairingTicket(Long.valueOf(id));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating the status of a ticket to "repaired".
     *
     * @param id the ID of the ticket.
     * @return the updated ticket or an error message.
     */
    @PutMapping("/repaired-ticket/{id}")
    @ApiOperation(value = "Update ticket status to 'repaired'", notes = "Updates the status of a specific ticket to 'repaired'.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ticket status updated to 'repaired' successfully."),
            @ApiResponse(code = 404, message = "Ticket, equipment, or technician not found.")
    })
    public ResponseEntity<?> repairedTicket(
            @ApiParam(value = "ID of the ticket", required = true) @PathVariable("id") String id) {
        try {
            var ticket = ticketService.repairedTicket(Long.valueOf(id));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException | EquipmentNotFoundException | TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating the status of a ticket to "failed".
     *
     * @param id the ID of the ticket.
     * @return the updated ticket or an error message.
     */
    @PutMapping("/failed-ticket/{id}")
    @ApiOperation(value = "Update ticket status to 'failed'", notes = "Updates the status of a specific ticket to 'failed'.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ticket status updated to 'failed' successfully."),
            @ApiResponse(code = 404, message = "Ticket, equipment, or technician not found.")
    })
    public ResponseEntity<?> failedTicket(
            @ApiParam(value = "ID of the ticket", required = true) @PathVariable("id") String id) {
        try {
            var ticket = ticketService.failedTicket(Long.valueOf(id));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException | EquipmentNotFoundException | TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
