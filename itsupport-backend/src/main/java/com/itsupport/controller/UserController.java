package com.itsupport.controller;

import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.AdminNotFoundException;
import com.itsupport.exception.ClientNotFoundException;
import com.itsupport.exception.TechnicianNotFoundException;
import com.itsupport.service.AdminService;
import com.itsupport.service.ClientService;
import com.itsupport.service.TechnicianService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user-related requests.
 *
 * This controller provides endpoints for managing users.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final AdminService adminService;

    private final ClientService clientService;

    private final TechnicianService technicianService;

    /**
     * Endpoint for retrieving all admins.
     *
     * @return a list of admins or an error message.
     */
    @GetMapping("/admin/get-all-admins")
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
    @GetMapping("/admin/get-admin-by-id/{id}")
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
    @PutMapping("/admin/update-admin/{id}")
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
    @DeleteMapping("/admin/delete-admin/{id}")
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
    @GetMapping("/admin/get-all-clients")
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
    @GetMapping("/admin/get-client-by-id/{id}")
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
    @DeleteMapping("/admin/delete-client/{id}")
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
    @GetMapping("/admin/get-all-technicians")
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
    @GetMapping("/admin/get-available-technicians")
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
    @GetMapping("/admin/get-technician-by-id/{id}")
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
    @DeleteMapping("/admin/delete-technician/{id}")
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
}
