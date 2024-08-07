package com.itsupport.controller;

import com.itsupport.dto.UserUpdateDto;
import com.itsupport.exception.BreakdownNotFoundException;
import com.itsupport.exception.ClientNotFoundException;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.service.BreakdownService;
import com.itsupport.service.ClientService;
import com.itsupport.service.EquipmentBreakdownService;
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

    private final EquipmentBreakdownService equipmentBreakdownService;

    private final BreakdownService breakdownService;

    @PutMapping("/update-client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto) {
        try {
            var updatedClient = clientService.updateClient(Long.valueOf(id), userUpdateDto);
            return ResponseEntity.ok(updatedClient);
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

    @GetMapping("/get-all-breakdowns")
    public ResponseEntity<?> getAllBreakdowns() {
        try {
            var breakdowns = breakdownService.getAllBreakdowns();
            return ResponseEntity.ok(breakdowns);
        } catch (BreakdownNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/report-breakdown/{equipmentId}&{breakdownId}")
    public ResponseEntity<?> reportBreakdown(@PathVariable("equipmentId") String equipmentId, @PathVariable("breakdownId") String breakdownId){
        try {
            var report = equipmentBreakdownService.reportBreakdown(Long.valueOf(equipmentId), Long.valueOf(breakdownId));
            return ResponseEntity.ok(report);
        } catch (EquipmentNotFoundException | BreakdownNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-all-reports/{id}")
    public ResponseEntity<?> getAll(@PathVariable("id") String id){
        return ResponseEntity.ok(equipmentBreakdownService.getAllBreakdownsByEquipment(Long.valueOf(id)));
    }

}
