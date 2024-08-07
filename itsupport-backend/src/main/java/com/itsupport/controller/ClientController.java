package com.itsupport.controller;

import com.itsupport.dto.TicketDto;
import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.*;
import com.itsupport.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update-client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedClient = clientService.updateClient(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedClient);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-equipments-by-client/{id}")
    public ResponseEntity<?> getEquipmentsByClient(@PathVariable("id") String id){
        try {
            var equipments = equipmentService.getEquipmentsByClient(Long.valueOf(id));
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-breakdowns")
    public ResponseEntity<?> getAllBreakdowns() {
        try {
            var breakdowns = breakdownService.getAllBreakdowns();
            return ResponseEntity.ok(breakdowns);
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/report-breakdown-ticket/{equipmentId}&{breakdownId}")
    public ResponseEntity<?> reportBreakdownWithTicket(@PathVariable("equipmentId") String equipmentId, @PathVariable("breakdownId") String breakdownId, @RequestBody TicketDto ticketDto) {
        try {
            var report = equipmentBreakdownService.reportBreakdown(Long.valueOf(equipmentId), Long.valueOf(breakdownId));
            var ticket = ticketService.createTicket(report, ticketDto);
            return ResponseEntity.ok(ticket);
        } catch (EquipmentNotFoundException | BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-tickets/{id}")
    public ResponseEntity<?> getAllTickets(@PathVariable("id") String id){
        try {
            var tickets = ticketService.getTicketsByClient(Long.valueOf(id));
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
