package com.itsupport.controller;

import com.itsupport.dto.EquipmentDto;
import com.itsupport.exception.ClientNotFoundException;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.service.EquipmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling equipment-related requests.
 *
 * This controller provides endpoints for managing equipments.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EquipmentController {

    private final EquipmentService equipmentService;

    /**
     * Endpoint for creating new equipment.
     *
     * @param equipmentDto the equipment details.
     * @return the created equipment or an error message.
     */
    @PostMapping("/admin/create-equipment")
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
    @GetMapping("/admin/get-all-equipments")
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
    @GetMapping("/admin/get-equipment-by-id/{id}")
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
    @PutMapping("/admin/update-equipment/{id}")
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
    @DeleteMapping("/admin/delete-equipment/{id}")
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
    @PutMapping("/admin/assign-equipment/{equipmentId}/to/{clientId}")
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
    @GetMapping("/admin/get-all-equipment-out-service")
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
     * Endpoint for retrieving equipment by client ID.
     *
     * @param id the ID of the client.
     * @return a list of equipment associated with the client or an error message.
     */
    @GetMapping("/client/get-equipments-by-client/{id}")
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

}
