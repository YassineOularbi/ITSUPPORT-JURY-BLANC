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

/**
 * Test class for {@link EquipmentBreakdownService}.
 *
 * This class contains unit tests for the {@link EquipmentBreakdownService} class, specifically focusing on the
 * `reportBreakdown` method. The tests ensure that the method behaves correctly in different scenarios involving
 * both equipment and breakdown entities.
 *
 * Dependencies:
 *
 * - {@link EquipmentBreakdownRepository} - Repository for managing {@link EquipmentBreakdown} entities.
 * - {@link EquipmentRepository} - Repository for managing {@link Equipment} entities.
 * - {@link BreakdownRepository} - Repository for managing breakdown entities.
 *
 * Test Methods:
 *
 * - {@link #reportBreakdown_EquipmentNotFoundException()} - Tests the case where the equipment is not found.
 * - {@link #reportBreakdown_BreakdownNotFoundException()} - Tests the case where the breakdown is not found.
 */
class EquipmentBreakdownServiceTest {

    @Mock
    private EquipmentBreakdownRepository equipmentBreakdownRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private BreakdownRepository breakdownRepository;

    @InjectMocks
    private EquipmentBreakdownService equipmentBreakdownService;

    /**
     * Initializes the test environment, including mock objects and test data setup.
     */
    public EquipmentBreakdownServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the case where the equipment is not found in the repository.
     * Verifies that an {@link EquipmentNotFoundException} is thrown and no further repository methods are called.
     */
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

    /**
     * Tests the case where the breakdown is not found in the repository.
     * Verifies that a {@link BreakdownNotFoundException} is thrown and no new {@link EquipmentBreakdown} is saved.
     */
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
