package com.itsupport.controller;

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
}
