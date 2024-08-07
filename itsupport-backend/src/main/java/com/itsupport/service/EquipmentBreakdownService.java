package com.itsupport.service;

import com.itsupport.enums.EquipmentStatus;
import com.itsupport.exception.BreakdownNotFoundException;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.model.EquipmentBreakdown;
import com.itsupport.model.EquipmentBreakdownKey;
import com.itsupport.repository.BreakdownRepository;
import com.itsupport.repository.EquipmentBreakdownRepository;
import com.itsupport.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentBreakdownService {

    private final EquipmentBreakdownRepository equipmentBreakdownRepository;

    private final EquipmentRepository equipmentRepository;

    private final BreakdownRepository breakdownRepository;

    public EquipmentBreakdown reportBreakdown(Long equipmentId, Long breakdownId){
        var equipment = equipmentRepository.findById(equipmentId).orElseThrow(EquipmentNotFoundException::new);
        var breakdown = breakdownRepository.findById(breakdownId).orElseThrow(BreakdownNotFoundException::new);
        equipment.setStatus(EquipmentStatus.BROKEN_DOWN);
        equipmentRepository.save(equipment);
        var report = new EquipmentBreakdown(new EquipmentBreakdownKey(equipment.getId(), breakdown.getId()), equipment, breakdown, null);
        return equipmentBreakdownRepository.save(report);
    }

}
