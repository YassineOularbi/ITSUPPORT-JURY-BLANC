package com.itsupport.controller;

import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.ClientNotFoundException;
import com.itsupport.exception.FileStorageException;
import com.itsupport.service.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling client-related requests.
 *
 * This controller provides endpoints for managing clients.
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
    @PutMapping(value = "/update-client/{id}", consumes = "multipart/form-data")
    @ApiOperation(value = "Update client", notes = "Updates the details of a specific client.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client updated successfully."),
            @ApiResponse(code = 404, message = "Client not found.")
    })
    public ResponseEntity<?> updateClient(
            @ApiParam(value = "ID of the client", required = true) @PathVariable("id") String id,
            @ApiParam(value = "Client update details", required = true) @ModelAttribute UserUpdateDto userUpdateDto) {
        try {
            var updatedClient = clientService.updateClient(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedClient);
        } catch (ClientNotFoundException | FileStorageException e) {
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
}
