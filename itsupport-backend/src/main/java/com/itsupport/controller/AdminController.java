package com.itsupport.controller;

import com.itsupport.dto.EquipmentDto;
import com.itsupport.exception.*;
import com.itsupport.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final EquipmentService equipmentService;

    @PostMapping("/create-equipment")
    public ResponseEntity<?> createEquipment(@RequestBody EquipmentDto equipmentDto) {
        try {
            var equipment = equipmentService.createEquipment(equipmentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-equipments")
    public ResponseEntity<?> getAllEquipments() {
        try {
            var equipments = equipmentService.getAllEquipments();
            return ResponseEntity.ok(equipments);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-equipment-by-id/{id}")
    public ResponseEntity<?> getEquipmentById(@PathVariable("id") String id) {
        try {
            var equipment = equipmentService.getEquipmentById(Long.valueOf(id));
            return ResponseEntity.ok(equipment);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update-equipment/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable("id") String id, @RequestBody EquipmentDto equipmentDto) {
        try {
            var updatedEquipment = equipmentService.updateEquipment(Long.valueOf(id), equipmentDto);
            return ResponseEntity.ok(updatedEquipment);
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-equipment/{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable("id") String id) {
        try {
            equipmentService.deleteEquipment(Long.valueOf(id));
            return ResponseEntity.noContent().build();
        } catch (EquipmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("assign-equipment/{equipmentId}/to/{clientId}")
    public ResponseEntity<?> assignEquipmentToClient(@PathVariable("equipmentId") String equipmentId, @PathVariable("clientId") String clientId) {
        try {
            var equipment = equipmentService.assignEquipmentToClient(Long.valueOf(equipmentId), Long.valueOf(clientId));
            return ResponseEntity.ok(equipment);
        } catch (EquipmentNotFoundException | ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
