package com.itsupport;

import com.itsupport.exception.BreakdownNotFoundException;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.model.Equipment;
import com.itsupport.model.EquipmentBreakdown;
import com.itsupport.repository.BreakdownRepository;
import com.itsupport.repository.EquipmentBreakdownRepository;
import com.itsupport.repository.EquipmentRepository;
import com.itsupport.service.EquipmentBreakdownService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipmentBreakdownServiceTest {

    @Mock
    private EquipmentBreakdownRepository equipmentBreakdownRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private BreakdownRepository breakdownRepository;

    @InjectMocks
    private EquipmentBreakdownService equipmentBreakdownService;

    public EquipmentBreakdownServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void reportBreakdown_EquipmentNotFoundException() {
        Long equipmentId = 1L;
        Long breakdownId = 2L;

        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.empty());

        assertThrows(EquipmentNotFoundException.class, () -> {
            equipmentBreakdownService.reportBreakdown(equipmentId, breakdownId);
        });

        verify(equipmentRepository, times(1)).findById(equipmentId);
        verify(breakdownRepository, times(0)).findById(breakdownId);
        verify(equipmentBreakdownRepository, times(0)).save(any(EquipmentBreakdown.class));
    }

    @Test
    void reportBreakdown_BreakdownNotFoundException() {
        Long equipmentId = 1L;
        Long breakdownId = 2L;

        Equipment equipment = new Equipment();
        equipment.setId(equipmentId);

        when(equipmentRepository.findById(equipmentId)).thenReturn(Optional.of(equipment));
        when(breakdownRepository.findById(breakdownId)).thenReturn(Optional.empty());

        assertThrows(BreakdownNotFoundException.class, () -> {
            equipmentBreakdownService.reportBreakdown(equipmentId, breakdownId);
        });

        verify(equipmentRepository, times(1)).findById(equipmentId);
        verify(breakdownRepository, times(1)).findById(breakdownId);
        verify(equipmentBreakdownRepository, times(0)).save(any(EquipmentBreakdown.class));
    }
}
