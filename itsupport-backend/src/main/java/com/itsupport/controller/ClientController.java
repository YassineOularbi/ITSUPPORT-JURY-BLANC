package com.itsupport.controller;

import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.ClientNotFoundException;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.service.ClientService;
import com.itsupport.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;

    private final EquipmentService equipmentService;

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

    @GetMapping("/get-equipments-by-client/{id}")
    public ResponseEntity<?> getEquipmentsByClient(@PathVariable("id") String id){
        try {
            var equipments = equipmentService.getEquipmentsByClient(Long.valueOf(id));
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
