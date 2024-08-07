package com.itsupport.auth.controller;

import com.itsupport.auth.exception.*;
import com.itsupport.auth.model.*;
import com.itsupport.auth.service.AuthService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication and registration requests.
 *
 * This controller provides endpoints for user authentication and registration
 * for different roles (admin, technician, and client).
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "Authentication Management", tags = {"Authentication Management"})
public class AuthController {

    private final AuthService userService;

    /**
     * Endpoint for user login and token generation.
     *
     * @param loginRequest the login request containing username and password.
     * @return the response entity containing the authentication response or an error message.
     */
    @ApiOperation(value = "Authenticate and get token", notes = "Authenticates the user and returns an authentication token.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully authenticated and retrieved the token"),
            @ApiResponse(code = 401, message = "Unauthorized - invalid username or password")
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(
            @ApiParam(value = "Login request containing username and password", required = true) @RequestBody LoginRequest loginRequest) {
        try {
            var authResponse = userService.login(loginRequest);
            return ResponseEntity.ok(authResponse);
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * Endpoint for admin registration.
     *
     * @param registerRequest the registration request containing user details.
     * @return the response entity containing the authentication response or an error message.
     */
    @ApiOperation(value = "Register an admin", notes = "Registers a new admin user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the admin"),
            @ApiResponse(code = 409, message = "Conflict - registration error")
    })
    @PostMapping("/register/admin")
    public ResponseEntity<?> adminRegister(
            @ApiParam(value = "Registration request containing user details", required = true) @RequestBody RegisterRequest registerRequest) {
        try {
            var authResponse = userService.adminRegister(registerRequest);
            return ResponseEntity.ok(authResponse);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Endpoint for technician registration.
     *
     * @param registerRequest the registration request containing user details.
     * @return the response entity containing the authentication response or an error message.
     */
    @ApiOperation(value = "Register a technician", notes = "Registers a new technician user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the technician"),
            @ApiResponse(code = 409, message = "Conflict - registration error")
    })
    @PostMapping("/register/technician")
    public ResponseEntity<?> technicianRegister(
            @ApiParam(value = "Registration request containing user details", required = true) @RequestBody RegisterRequest registerRequest) {
        try {
            var authResponse = userService.technicianRegister(registerRequest);
            return ResponseEntity.ok(authResponse);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Endpoint for client registration.
     *
     * @param registerRequest the registration request containing user details.
     * @return the response entity containing the authentication response or an error message.
     */
    @ApiOperation(value = "Register a client", notes = "Registers a new client user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered the client"),
            @ApiResponse(code = 409, message = "Conflict - registration error")
    })
    @PostMapping("/register/client")
    public ResponseEntity<?> clientRegister(
            @ApiParam(value = "Registration request containing user details", required = true) @RequestBody RegisterRequest registerRequest) {
        try {
            var authResponse = userService.clientRegister(registerRequest);
            return ResponseEntity.ok(authResponse);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
