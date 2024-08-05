package com.itsupport.auth.controller;

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
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PostMapping("/admin/signup")
//    public ResponseEntity<?> adminRegister(@RequestBody RegisterRequest registerRequest) {
//        try {
//            var authResponse = userService.adminRegister(registerRequest);
//            return ResponseEntity.ok(authResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/technician/signup")
//    public ResponseEntity<?> technicianRegister(@RequestBody RegisterRequest registerRequest) {
//        try {
//            var authResponse = userService.technicianRegister(registerRequest);
//            return ResponseEntity.ok(authResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/client/signup")
//    public ResponseEntity<?> clientRegister(@RequestBody RegisterRequest registerRequest) {
//        try {
//            var authResponse = userService.clientRegister(registerRequest);
//            return ResponseEntity.ok(authResponse);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}
