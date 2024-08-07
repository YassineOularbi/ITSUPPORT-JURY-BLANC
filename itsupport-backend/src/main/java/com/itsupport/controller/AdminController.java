package com.itsupport.controller;

import com.itsupport.dto.*;
import com.itsupport.exception.*;
import com.itsupport.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final EquipmentService equipmentService;

    private final AdminService adminService;

    private final ClientService clientService;

    private final TechnicianService technicianService;

    private final BreakdownService breakdownService;

    private final TicketService ticketService;

    @GetMapping("/get-all-admins")
    public ResponseEntity<?> getAllAdmins(){
        try {
            var admins = adminService.getAllAdmins();
            return ResponseEntity.ok(admins);
        } catch (AdminNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-admin-by-id/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") String id){
        try {
            var admin = adminService.getAdminById(Long.valueOf(id));
            return ResponseEntity.ok(admin);
        } catch (AdminNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-admin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto){
        try {
            var updatedAdmin = adminService.updateAdmin(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedAdmin);
        } catch (AdminNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") String id){
        try {
            adminService.deleteAdmin(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (AdminNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-clients")
    public ResponseEntity<?> getAllClients() {
        try {
            var clients = clientService.getAllClients();
            return ResponseEntity.ok(clients);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-client-by-id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable("id") String id) {
        try {
            var client = clientService.getClientById(Long.valueOf(id));
            return ResponseEntity.ok(client);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedClient = clientService.updateClient(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedClient);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") String id) {
        try {
            clientService.deleteClient(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-technicians")
    public ResponseEntity<?> getAllTechnicians() {
        try {
            var technicians = technicianService.getAllTechnicians();
            return ResponseEntity.ok(technicians);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-available-technicians")
    public ResponseEntity<?> getAvailableTechnicians() {
        try {
            var technicians = technicianService.getAvailableTechnicians();
            return ResponseEntity.ok(technicians);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-technician-by-id/{id}")
    public ResponseEntity<?> getTechnicianById(@PathVariable("id") String id) {
        try {
            var technician = technicianService.getTechnicianById(Long.valueOf(id));
            return ResponseEntity.ok(technician);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-technician/{id}")
    public ResponseEntity<?> updateTechnician(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedTechnician = technicianService.updateTechnician(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedTechnician);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-technician/{id}")
    public ResponseEntity<?> deleteTechnician(@PathVariable("id") String id) {
        try {
            technicianService.deleteTechnician(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create-equipment")
    public ResponseEntity<?> createEquipment(@RequestBody EquipmentDto equipmentDto) {
        try {
            var equipment = equipmentService.createEquipment(equipmentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-equipments")
    public ResponseEntity<?> getAllEquipments() {
        try {
            var equipments = equipmentService.getAllEquipments();
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-equipment-by-id/{id}")
    public ResponseEntity<?> getEquipmentById(@PathVariable("id") String id) {
        try {
            var equipment = equipmentService.getEquipmentById(Long.valueOf(id));
            return ResponseEntity.ok(equipment);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-equipment/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable("id") String id, @RequestBody EquipmentDto equipmentDto) {
        try {
            var updatedEquipment = equipmentService.updateEquipment(Long.valueOf(id), equipmentDto);
            return ResponseEntity.ok(updatedEquipment);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-equipment/{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable("id") String id) {
        try {
            equipmentService.deleteEquipment(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/assign-equipment/{equipmentId}/to/{clientId}")
    public ResponseEntity<?> assignEquipmentToClient(@PathVariable("equipmentId") String equipmentId, @PathVariable("clientId") String clientId) {
        try {
            var equipment = equipmentService.assignEquipmentToClient(Long.valueOf(equipmentId), Long.valueOf(clientId));
            return ResponseEntity.ok(equipment);
        } catch (EquipmentNotFoundException | ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-equipment-out-service")
    public ResponseEntity<?> getAllEquipmentOutService(){
        try {
            var equipments = equipmentService.getAllEquipmentOutService();
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/create-breakdown")
    public ResponseEntity<?> createBreakdown(@RequestBody BreakdownDto breakdownDto) {
        try {
            var breakdown = breakdownService.createBreakdown(breakdownDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(breakdown);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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

    @GetMapping("/get-breakdown-by-id/{id}")
    public ResponseEntity<?> getBreakdownById(@PathVariable("id") String id) {
        try {
            var breakdown = breakdownService.getBreakdownById(Long.valueOf(id));
            return ResponseEntity.ok(breakdown);
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-breakdown/{id}")
    public ResponseEntity<?> updateBreakdown(@PathVariable("id") String id, @RequestBody BreakdownDto breakdownDto) {
        try {
            var updatedBreakdown = breakdownService.updateBreakdown(Long.valueOf(id), breakdownDto);
            return ResponseEntity.ok(updatedBreakdown);
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-breakdown/{id}")
    public ResponseEntity<?> deleteBreakdown(@PathVariable("id") String id) {
        try {
            breakdownService.deleteBreakdown(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping("/assign-ticket/{ticketId}/to/{technicianId}")
    public ResponseEntity<?> assignTicketToTechnician(@PathVariable("ticketId") String ticketId, @PathVariable("technicianId") String technicianId){
        try {
            var ticket = ticketService.assignTicketToTechnician(Long.valueOf(ticketId), Long.valueOf(technicianId));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException | TechnicianNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-tickets")
    public ResponseEntity<?> getAllTickets(){
        try {
            var tickets = ticketService.getAllTickets();
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-pending-tickets")
    public ResponseEntity<?> getPendingTickets(){
        try {
            var tickets = ticketService.getPendingTickets();
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
