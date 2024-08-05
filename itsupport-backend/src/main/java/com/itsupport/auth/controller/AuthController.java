package com.itsupport.auth.controller;

import com.itsupport.auth.exception.LoginException;
import com.itsupport.auth.exception.RegistrationException;
import com.itsupport.auth.model.*;
import com.itsupport.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        try {
            var authResponse = userService.login(loginRequest);
            return ResponseEntity.ok(authResponse);
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @PostMapping("/register/admin")
    public ResponseEntity<?> adminRegister(@RequestBody RegisterRequest registerRequest) {
        try {
            var authResponse = userService.adminRegister(registerRequest);
            return ResponseEntity.ok(authResponse);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/register/technician")
    public ResponseEntity<?> technicianRegister(@RequestBody RegisterRequest registerRequest) {
        try {
            var authResponse = userService.technicianRegister(registerRequest);
            return ResponseEntity.ok(authResponse);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/register/client")
    public ResponseEntity<?> clientRegister(@RequestBody RegisterRequest registerRequest) {
        try {
            var authResponse = userService.clientRegister(registerRequest);
            return ResponseEntity.ok(authResponse);
        } catch (RegistrationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
