package com.itsupport.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TestController {

    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "this is admin";
    }

    @GetMapping("/technician")
//    @PreAuthorize("hasRole('TECHNICIAN')")
    public String technician(){
        return "this is technician";
    }

    @GetMapping("/client")
//    @PreAuthorize("hasRole('CLIENT')")
    public String client(){
        return "this is client";
    }
}
