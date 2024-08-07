package com.itsupport.controller;

import com.itsupport.dto.*;
import com.itsupport.exception.*;
import com.itsupport.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiParam;

/**
 * Controller for handling admin-related requests.
 *
 * This controller provides endpoints for managing admins, clients, technicians, equipment, breakdowns, and tickets.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
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

    /**
     * Endpoint for retrieving all admins.
     *
     * @return a list of admins or an error message.
     */
    @GetMapping("/get-all-admins")
    @ApiOperation(value = "Get all admins", notes = "Retrieves a list of all admins.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of admins retrieved successfully."),
            @ApiResponse(code = 404, message = "Admins not found.")
    })
    public ResponseEntity<?> getAllAdmins() {
        try {
            var admins = adminService.getAllAdmins();
            return ResponseEntity.ok(admins);
        } catch (AdminNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving an admin by ID.
     *
     * @param id the ID of the admin.
     * @return the admin or an error message.
     */
    @GetMapping("/get-admin-by-id/{id}")
    @ApiOperation(value = "Get admin by ID", notes = "Retrieves a specific admin by their ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Admin retrieved successfully."),
            @ApiResponse(code = 404, message = "Admin not found.")
    })
    public ResponseEntity<?> getAdminById(
            @ApiParam(value = "ID of the admin", required = true) @PathVariable("id") String id) {
        try {
            var admin = adminService.getAdminById(Long.valueOf(id));
            return ResponseEntity.ok(admin);
        } catch (AdminNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating an admin.
     *
     * @param id the ID of the admin.
     * @param userUpdateDto the admin update details.
     * @return the updated admin or an error message.
     */
    @PutMapping("/update-admin/{id}")
    @ApiOperation(value = "Update admin", notes = "Updates the details of a specific admin.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Admin updated successfully."),
            @ApiResponse(code = 404, message = "Admin not found.")
    })
    public ResponseEntity<?> updateAdmin(
            @ApiParam(value = "ID of the admin", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Admin update details", required = true) @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedAdmin = adminService.updateAdmin(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedAdmin);
        } catch (AdminNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for deleting an admin.
     *
     * @param id the ID of the admin.
     * @return a no content response or an error message.
     */
    @DeleteMapping("/delete-admin/{id}")
    @ApiOperation(value = "Delete admin", notes = "Deletes a specific admin by their ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Admin deleted successfully."),
            @ApiResponse(code = 404, message = "Admin not found.")
    })
    public ResponseEntity<?> deleteAdmin(
            @ApiParam(value = "ID of the admin", required = true) @PathVariable("id") String id) {
        try {
            adminService.deleteAdmin(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (AdminNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all clients.
     *
     * @return a list of clients or an error message.
     */
    @GetMapping("/get-all-clients")
    @ApiOperation(value = "Get all clients", notes = "Retrieves a list of all clients.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of clients retrieved successfully."),
            @ApiResponse(code = 404, message = "Clients not found.")
    })
    public ResponseEntity<?> getAllClients() {
        try {
            var clients = clientService.getAllClients();
            return ResponseEntity.ok(clients);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving a client by ID.
     *
     * @param id the ID of the client.
     * @return the client or an error message.
     */
    @GetMapping("/get-client-by-id/{id}")
    @ApiOperation(value = "Get client by ID", notes = "Retrieves a specific client by their ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client retrieved successfully."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    public ResponseEntity<?> getClientById(
            @ApiParam(value = "ID of the client", required = true) @PathVariable("id") String id) {
        try {
            var client = clientService.getClientById(Long.valueOf(id));
            return ResponseEntity.ok(client);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating a client.
     *
     * @param id the ID of the client.
     * @param userUpdateDto the client update details.
     * @return the updated client or an error message.
     */
    @PutMapping("/update-client/{id}")
    @ApiOperation(value = "Update client", notes = "Updates the details of a specific client.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client updated successfully."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    public ResponseEntity<?> updateClient(
            @ApiParam(value = "ID of the client", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Client update details", required = true) @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedClient = clientService.updateClient(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedClient);
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for deleting a client.
     *
     * @param id the ID of the client.
     * @return a no content response or an error message.
     */
    @DeleteMapping("/delete-client/{id}")
    @ApiOperation(value = "Delete client", notes = "Deletes a specific client by their ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Client deleted successfully."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    public ResponseEntity<?> deleteClient(
            @ApiParam(value = "ID of the client", required = true) @PathVariable("id") String id) {
        try {
            clientService.deleteClient(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all technicians.
     *
     * @return a list of technicians or an error message.
     */
    @GetMapping("/get-all-technicians")
    @ApiOperation(value = "Get all technicians", notes = "Retrieves a list of all technicians.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of technicians retrieved successfully."),
            @ApiResponse(code = 404, message = "Technicians not found.")
    })
    public ResponseEntity<?> getAllTechnicians() {
        try {
            var technicians = technicianService.getAllTechnicians();
            return ResponseEntity.ok(technicians);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving available technicians.
     *
     * @return a list of available technicians or an error message.
     */
    @GetMapping("/get-available-technicians")
    @ApiOperation(value = "Get available technicians", notes = "Retrieves a list of available technicians.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of available technicians retrieved successfully."),
            @ApiResponse(code = 404, message = "Technicians not found.")
    })
    public ResponseEntity<?> getAvailableTechnicians() {
        try {
            var technicians = technicianService.getAvailableTechnicians();
            return ResponseEntity.ok(technicians);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving a technician by ID.
     *
     * @param id the ID of the technician.
     * @return the technician or an error message.
     */
    @GetMapping("/get-technician-by-id/{id}")
    @ApiOperation(value = "Get technician by ID", notes = "Retrieves a specific technician by their ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Technician retrieved successfully."),
            @ApiResponse(code = 404, message = "Technician not found.")
    })
    public ResponseEntity<?> getTechnicianById(
            @ApiParam(value = "ID of the technician", required = true) @PathVariable("id") String id) {
        try {
            var technician = technicianService.getTechnicianById(Long.valueOf(id));
            return ResponseEntity.ok(technician);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating a technician.
     *
     * @param id the ID of the technician.
     * @param userUpdateDto the technician update details.
     * @return the updated technician or an error message.
     */
    @PutMapping("/update-technician/{id}")
    @ApiOperation(value = "Update technician", notes = "Updates the details of a specific technician.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Technician updated successfully."),
            @ApiResponse(code = 404, message = "Technician not found.")
    })
    public ResponseEntity<?> updateTechnician(
            @ApiParam(value = "ID of the technician", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Technician update details", required = true) @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedTechnician = technicianService.updateTechnician(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedTechnician);
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for deleting a technician.
     *
     * @param id the ID of the technician.
     * @return a no content response or an error message.
     */
    @DeleteMapping("/delete-technician/{id}")
    @ApiOperation(value = "Delete technician", notes = "Deletes a specific technician by their ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Technician deleted successfully."),
            @ApiResponse(code = 404, message = "Technician not found.")
    })
    public ResponseEntity<?> deleteTechnician(
            @ApiParam(value = "ID of the technician", required = true) @PathVariable("id") String id) {
        try {
            technicianService.deleteTechnician(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for creating new equipment.
     *
     * @param equipmentDto the equipment details.
     * @return the created equipment or an error message.
     */
    @PostMapping("/create-equipment")
    @ApiOperation(value = "Create new equipment", notes = "Creates a new piece of equipment.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Equipment created successfully."),
            @ApiResponse(code = 400, message = "Invalid equipment details.")
    })
    public ResponseEntity<?> createEquipment(
            @ApiParam(value = "Equipment details", required = true) @RequestBody EquipmentDto equipmentDto) {
        try {
            var equipment = equipmentService.createEquipment(equipmentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all equipment.
     *
     * @return a list of equipment or an error message.
     */
    @GetMapping("/get-all-equipments")
    @ApiOperation(value = "Get all equipment", notes = "Retrieves a list of all equipment.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of equipment retrieved successfully."),
            @ApiResponse(code = 404, message = "Equipment not found.")
    })
    public ResponseEntity<?> getAllEquipments() {
        try {
            var equipments = equipmentService.getAllEquipments();
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving equipment by ID.
     *
     * @param id the ID of the equipment.
     * @return the equipment or an error message.
     */
    @GetMapping("/get-equipment-by-id/{id}")
    @ApiOperation(value = "Get equipment by ID", notes = "Retrieves a specific piece of equipment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Equipment retrieved successfully."),
            @ApiResponse(code = 404, message = "Equipment not found.")
    })
    public ResponseEntity<?> getEquipmentById(
            @ApiParam(value = "ID of the equipment", required = true) @PathVariable("id") String id) {
        try {
            var equipment = equipmentService.getEquipmentById(Long.valueOf(id));
            return ResponseEntity.ok(equipment);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating equipment details.
     *
     * @param id the ID of the equipment.
     * @param equipmentDto the updated equipment details.
     * @return the updated equipment or an error message.
     */
    @PutMapping("/update-equipment/{id}")
    @ApiOperation(value = "Update equipment", notes = "Updates the details of a specific piece of equipment.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Equipment updated successfully."),
            @ApiResponse(code = 404, message = "Equipment not found.")
    })
    public ResponseEntity<?> updateEquipment(
            @ApiParam(value = "ID of the equipment", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Updated equipment details", required = true) @RequestBody EquipmentDto equipmentDto) {
        try {
            var updatedEquipment = equipmentService.updateEquipment(Long.valueOf(id), equipmentDto);
            return ResponseEntity.ok(updatedEquipment);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for deleting equipment.
     *
     * @param id the ID of the equipment.
     * @return a no content response or an error message.
     */
    @DeleteMapping("/delete-equipment/{id}")
    @ApiOperation(value = "Delete equipment", notes = "Deletes a specific piece of equipment by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Equipment deleted successfully."),
            @ApiResponse(code = 404, message = "Equipment not found.")
    })
    public ResponseEntity<?> deleteEquipment(
            @ApiParam(value = "ID of the equipment", required = true) @PathVariable("id") String id) {
        try {
            equipmentService.deleteEquipment(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for assigning equipment to a client.
     *
     * @param equipmentId the ID of the equipment.
     * @param clientId the ID of the client.
     * @return the assigned equipment or an error message.
     */
    @PutMapping("/assign-equipment/{equipmentId}/to/{clientId}")
    @ApiOperation(value = "Assign equipment to client", notes = "Assigns a specific piece of equipment to a client.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Equipment assigned to client successfully."),
            @ApiResponse(code = 404, message = "Equipment or client not found.")
    })
    public ResponseEntity<?> assignEquipmentToClient(
            @ApiParam(value = "ID of the equipment", required = true) @PathVariable("equipmentId") String equipmentId,
            @ApiParam(value = "ID of the client", required = true) @PathVariable("clientId") String clientId) {
        try {
            var equipment = equipmentService.assignEquipmentToClient(Long.valueOf(equipmentId), Long.valueOf(clientId));
            return ResponseEntity.ok(equipment);
        } catch (EquipmentNotFoundException | ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all equipment that are out of service.
     *
     * @return a list of out-of-service equipment or an error message.
     */
    @GetMapping("/get-all-equipment-out-service")
    @ApiOperation(value = "Get all equipment out of service", notes = "Retrieves a list of all equipment that are out of service.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of out-of-service equipment retrieved successfully."),
            @ApiResponse(code = 404, message = "Equipment not found.")
    })
    public ResponseEntity<?> getAllEquipmentOutService() {
        try {
            var equipments = equipmentService.getAllEquipmentOutService();
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for creating a new breakdown.
     *
     * @param breakdownDto the breakdown details.
     * @return the created breakdown or an error message.
     */
    @PostMapping("/create-breakdown")
    @ApiOperation(value = "Create new breakdown", notes = "Creates a new breakdown entry.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Breakdown created successfully."),
            @ApiResponse(code = 400, message = "Invalid breakdown details.")
    })
    public ResponseEntity<?> createBreakdown(
            @ApiParam(value = "Breakdown details", required = true) @RequestBody BreakdownDto breakdownDto) {
        try {
            var breakdown = breakdownService.createBreakdown(breakdownDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(breakdown);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all breakdowns.
     *
     * @return a list of breakdowns or an error message.
     */
    @GetMapping("/get-all-breakdowns")
    @ApiOperation(value = "Get all breakdowns", notes = "Retrieves a list of all breakdowns.")
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
     * Endpoint for retrieving a breakdown by ID.
     *
     * @param id the ID of the breakdown.
     * @return the breakdown or an error message.
     */
    @GetMapping("/get-breakdown-by-id/{id}")
    @ApiOperation(value = "Get breakdown by ID", notes = "Retrieves a specific breakdown by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Breakdown retrieved successfully."),
            @ApiResponse(code = 404, message = "Breakdown not found.")
    })
    public ResponseEntity<?> getBreakdownById(
            @ApiParam(value = "ID of the breakdown", required = true) @PathVariable("id") String id) {
        try {
            var breakdown = breakdownService.getBreakdownById(Long.valueOf(id));
            return ResponseEntity.ok(breakdown);
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for updating a breakdown.
     *
     * @param id the ID of the breakdown.
     * @param breakdownDto the updated breakdown details.
     * @return the updated breakdown or an error message.
     */
    @PutMapping("/update-breakdown/{id}")
    @ApiOperation(value = "Update breakdown", notes = "Updates the details of a specific breakdown.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Breakdown updated successfully."),
            @ApiResponse(code = 404, message = "Breakdown not found.")
    })
    public ResponseEntity<?> updateBreakdown(
            @ApiParam(value = "ID of the breakdown", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Updated breakdown details", required = true) @RequestBody BreakdownDto breakdownDto) {
        try {
            var updatedBreakdown = breakdownService.updateBreakdown(Long.valueOf(id), breakdownDto);
            return ResponseEntity.ok(updatedBreakdown);
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for deleting a breakdown.
     *
     * @param id the ID of the breakdown.
     * @return a no content response or an error message.
     */
    @DeleteMapping("/delete-breakdown/{id}")
    @ApiOperation(value = "Delete breakdown", notes = "Deletes a specific breakdown by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Breakdown deleted successfully."),
            @ApiResponse(code = 404, message = "Breakdown not found.")
    })
    public ResponseEntity<?> deleteBreakdown(
            @ApiParam(value = "ID of the breakdown", required = true) @PathVariable("id") String id) {
        try {
            breakdownService.deleteBreakdown(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for assigning a ticket to a technician.
     *
     * @param ticketId the ID of the ticket.
     * @param technicianId the ID of the technician.
     * @return the assigned ticket or an error message.
     */
    @PutMapping("/assign-ticket/{ticketId}/to/{technicianId}")
    @ApiOperation(value = "Assign ticket to technician", notes = "Assigns a specific ticket to a technician.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ticket assigned to technician successfully."),
            @ApiResponse(code = 404, message = "Ticket or technician not found.")
    })
    public ResponseEntity<?> assignTicketToTechnician(
            @ApiParam(value = "ID of the ticket", required = true) @PathVariable("ticketId") String ticketId,
            @ApiParam(value = "ID of the technician", required = true) @PathVariable("technicianId") String technicianId) {
        try {
            var ticket = ticketService.assignTicketToTechnician(Long.valueOf(ticketId), Long.valueOf(technicianId));
            return ResponseEntity.ok(ticket);
        } catch (TicketNotFoundException | TechnicianNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all tickets.
     *
     * @return a list of tickets or an error message.
     */
    @GetMapping("/get-all-tickets")
    @ApiOperation(value = "Get all tickets", notes = "Retrieves a list of all tickets.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of tickets retrieved successfully."),
            @ApiResponse(code = 404, message = "Tickets not found.")
    })
    public ResponseEntity<?> getAllTickets() {
        try {
            var tickets = ticketService.getAllTickets();
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving pending tickets.
     *
     * @return a list of pending tickets or an error message.
     */
    @GetMapping("/get-pending-tickets")
    @ApiOperation(value = "Get pending tickets", notes = "Retrieves a list of all pending tickets.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of pending tickets retrieved successfully."),
            @ApiResponse(code = 404, message = "Tickets not found.")
    })
    public ResponseEntity<?> getPendingTickets() {
        try {
            var tickets = ticketService.getPendingTickets();
            return ResponseEntity.ok(tickets);
        } catch (TicketNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
