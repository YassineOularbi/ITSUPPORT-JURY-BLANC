package com.itsupport.controller;

import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.exception.TechnicianNotFoundException;
import com.itsupport.exception.TicketNotFoundException;
import com.itsupport.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technician")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TechnicianController {

    private final TicketService ticketService;

    @GetMapping("/get-processing-tickets/{id}")
    public ResponseEntity<?> getProcessingTickets(@PathVariable("id") String id){
        try {
            var tickets = ticketService.getProcessingTickets(Long.valueOf(id));
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-tickets/{id}")
    public ResponseEntity<?> getAllTickets(@PathVariable("id") String id){
        try {
            var tickets = ticketService.getTicketsByTechnician(Long.valueOf(id));
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/repairing-ticket/{id}")
    public ResponseEntity<?> repairingTicket(@PathVariable("id") String id) {
        try {
            var ticket = ticketService.repairingTicket(Long.valueOf(id));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/repaired-ticket/{id}")
    public ResponseEntity<?> repairedTicket(@PathVariable("id") String id) {
        try {
            var ticket = ticketService.repairedTicket(Long.valueOf(id));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException | EquipmentNotFoundException | TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/failed-ticket/{id}")
    public ResponseEntity<?> failedTicket(@PathVariable("id") String id) {
        try {
            var ticket = ticketService.failedTicket(Long.valueOf(id));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException | EquipmentNotFoundException | TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
