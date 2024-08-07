package com.itsupport.service;

import com.itsupport.dto.EquipmentDto;
import com.itsupport.enums.EquipmentStatus;
import com.itsupport.exception.*;
import com.itsupport.mapper.EquipmentMapper;
import com.itsupport.model.Equipment;
import com.itsupport.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    private final ClientRepository clientRepository;

    public Equipment createEquipment(EquipmentDto equipmentDto) {
        var equipment = equipmentMapper.toEntity(equipmentDto);
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipments() {
        var equipments = equipmentRepository.findAll();
        if (equipments.isEmpty()){
           throw new EquipmentNotFoundException();
        }
        return equipments;
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(EquipmentNotFoundException::new);
    }

    public Equipment updateEquipment(Long id, EquipmentDto equipmentDto) {
        var equipment = equipmentRepository.findById(id).orElseThrow(EquipmentNotFoundException::new);
        var updatedEquipment = equipmentMapper.partialUpdate(equipmentDto, equipment);
        return equipmentRepository.save(updatedEquipment);
    }

    public void deleteEquipment(Long id) {
        var equipment = equipmentRepository.findById(id).orElseThrow(EquipmentNotFoundException::new);
        equipmentRepository.delete(equipment);
    }

    public Equipment assignEquipmentToClient(Long equipmentId, Long clientId){
        var equipment = equipmentRepository.findById(equipmentId).orElseThrow(EquipmentNotFoundException::new);
        var client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
        equipment.setClient(client);
        equipment.setStatus(EquipmentStatus.IN_SERVICE);
        return equipmentRepository.save(equipment);
    }

    public List<Equipment> getAllEquipmentOutService(){
        var equipments = equipmentRepository.getEquipmentByStatus(EquipmentStatus.OUT_OF_SERVICE);
        if (equipments.isEmpty()){
            throw new EquipmentNotFoundException();
        }
        return equipments;
    }

    public List<Equipment> getEquipmentsByClient(Long id){
        var equipments = equipmentRepository.getEquipmentByClientId(id);
        if (equipments.isEmpty()){
            throw new EquipmentNotFoundException();
        }
        return equipments;
    }
}
