package com.itsupport.controller;

import com.itsupport.dto.BreakdownDto;
import com.itsupport.exception.BreakdownNotFoundException;
import com.itsupport.service.BreakdownService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling breakdown-related requests.
 *
 * This controller provides endpoints for managing breakdowns.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */

@RestController
@RequestMapping("/api/breakdown")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BreakdownController {

    private final BreakdownService breakdownService;

    /**
     * Endpoint for creating a new breakdown.
     *
     * @param breakdownDto the breakdown details.
     * @return the created breakdown or an error message.
     */
    @PostMapping("/admin/create-breakdown")
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
    @GetMapping("/admin/get-breakdown-by-id/{id}")
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
    @PutMapping("/admin/update-breakdown/{id}")
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
    @DeleteMapping("/admin/delete-breakdown/{id}")
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

}
