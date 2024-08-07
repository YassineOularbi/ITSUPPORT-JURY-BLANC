package com.itsupport.controller;

import com.itsupport.dto.*;
import com.itsupport.exception.*;
import com.itsupport.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

/**
 * Controller for handling client-related requests.
 *
 * This controller provides endpoints for updating clients, retrieving equipment,
 * reporting breakdowns with tickets, and fetching tickets for clients.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;
    private final EquipmentService equipmentService;
    private final EquipmentBreakdownService equipmentBreakdownService;
    private final BreakdownService breakdownService;
    private final TicketService ticketService;

    /**
     * Endpoint for updating client details.
     *
     * @param id the ID of the client to update.
     * @param userUpdateDto the details to update the client with.
     * @return the updated client details or an error message.
     */
    @PutMapping("/update-client/{id}")
    @ApiOperation(value = "Update client details", notes = "Updates the details of a specific client.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client details updated successfully."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    public ResponseEntity<?> updateClient(
            @ApiParam(value = "ID of the client to update", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Client update details", required = true) @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedClient = clientService.updateClient(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedClient);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving equipment by client ID.
     *
     * @param id the ID of the client.
     * @return a list of equipment associated with the client or an error message.
     */
    @GetMapping("/get-equipments-by-client/{id}")
    @ApiOperation(value = "Get equipment by client ID", notes = "Retrieves all equipment associated with a specific client.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of equipment retrieved successfully."),
            @ApiResponse(code = 404, message = "Equipment not found.")
    })
    public ResponseEntity<?> getEquipmentsByClient(
            @ApiParam(value = "ID of the client", required = true) @PathVariable("id") String id) {
        try {
            var equipments = equipmentService.getEquipmentsByClient(Long.valueOf(id));
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all breakdowns.
     *
     * @return a list of all breakdowns or an error message.
     */
    @GetMapping("/get-all-breakdowns")
    @ApiOperation(value = "Get all breakdowns", notes = "Retrieves all breakdowns in the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of breakdowns retrieved successfully."),
            @ApiResponse(code = 404, message = "Breakdowns not found.")
    })
    public ResponseEntity<?> getAllBreakdowns() {
        try {
            var breakdowns = breakdownService.getAllBreakdowns();
            return ResponseEntity.ok(breakdowns);
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for reporting a breakdown with a ticket.
     *
     * @param equipmentId the ID of the equipment.
     * @param breakdownId the ID of the breakdown.
     * @param ticketDto the ticket details.
     * @return the created ticket or an error message.
     */
    @PostMapping("/report-breakdown-ticket/{equipmentId}&{breakdownId}")
    @ApiOperation(value = "Report breakdown with ticket", notes = "Reports a breakdown for equipment and creates a ticket.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Breakdown reported and ticket created successfully."),
            @ApiResponse(code = 404, message = "Equipment or breakdown or client not found.")
    })
    public ResponseEntity<?> reportBreakdownWithTicket(
            @ApiParam(value = "ID of the equipment", required = true) @PathVariable("equipmentId") String equipmentId,
            @ApiParam(value = "ID of the breakdown", required = true) @PathVariable("breakdownId") String breakdownId,
            @ApiParam(value = "Ticket details", required = true) @RequestBody TicketDto ticketDto) {
        try {
            var report = equipmentBreakdownService.reportBreakdown(Long.valueOf(equipmentId), Long.valueOf(breakdownId));
            var ticket = ticketService.createTicket(report, ticketDto);
            return ResponseEntity.ok(ticket);
        } catch (EquipmentNotFoundException | BreakdownNotFoundException | ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all tickets by client ID.
     *
     * @param id the ID of the client.
     * @return a list of all tickets associated with the client or an error message.
     */
    @GetMapping("/get-all-tickets/{id}")
    @ApiOperation(value = "Get all tickets by client ID", notes = "Retrieves all tickets associated with a specific client.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of tickets retrieved successfully."),
            @ApiResponse(code = 404, message = "Tickets not found.")
    })
    public ResponseEntity<?> getAllTickets(
            @ApiParam(value = "ID of the client", required = true) @PathVariable("id") String id) {
        try {
            var tickets = ticketService.getTicketsByClient(Long.valueOf(id));
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
