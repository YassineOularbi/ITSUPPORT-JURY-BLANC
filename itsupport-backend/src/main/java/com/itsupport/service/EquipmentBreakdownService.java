package com.itsupport.service;

import com.itsupport.enums.EquipmentStatus;
import com.itsupport.exception.BreakdownNotFoundException;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.model.Breakdown;
import com.itsupport.model.Equipment;
import com.itsupport.model.EquipmentBreakdown;
import com.itsupport.model.EquipmentBreakdownKey;
import com.itsupport.repository.BreakdownRepository;
import com.itsupport.repository.EquipmentBreakdownRepository;
import com.itsupport.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing the relationship between {@link Equipment} and {@link Breakdown} entities.
 *
 * The EquipmentBreakdownService class provides business logic for reporting breakdowns associated with equipment.
 * It interacts with {@link EquipmentRepository}, {@link BreakdownRepository}, and {@link EquipmentBreakdownRepository} to manage
 * the reporting of breakdowns. This service handles the operation of associating an equipment with a breakdown and updating
 * the equipment's status accordingly.
 *
 * Dependencies:
 *
 * - {@link EquipmentBreakdownRepository} - The repository used for CRUD operations on {@link EquipmentBreakdown} entities.
 * - {@link EquipmentRepository} - The repository used for CRUD operations on {@link Equipment} entities.
 * - {@link BreakdownRepository} - The repository used for CRUD operations on {@link Breakdown} entities.
 *
 * Methods:
 *
 * - {@link #reportBreakdown(Long, Long)} - Reports a breakdown for a specific piece of equipment. Updates the equipment's status
 *   to {@link EquipmentStatus#BROKEN_DOWN} and creates an {@link EquipmentBreakdown} association. Throws {@link EquipmentNotFoundException}
 *   if the equipment is not found, and {@link BreakdownNotFoundException} if the breakdown is not found.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.model.Breakdown
 * @see com.itsupport.model.EquipmentBreakdown
 * @see com.itsupport.model.EquipmentBreakdownKey
 * @see com.itsupport.repository.EquipmentBreakdownRepository
 * @see com.itsupport.repository.EquipmentRepository
 * @see com.itsupport.repository.BreakdownRepository
 * @see com.itsupport.enums.EquipmentStatus
 * @see com.itsupport.exception.BreakdownNotFoundException
 * @see com.itsupport.exception.EquipmentNotFoundException
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentBreakdownService {

    private final EquipmentBreakdownRepository equipmentBreakdownRepository;
    private final EquipmentRepository equipmentRepository;
    private final BreakdownRepository breakdownRepository;

    /**
     * Reports a breakdown for a specific piece of equipment.
     *
     * This method updates the status of the specified equipment to {@link EquipmentStatus#BROKEN_DOWN}
     * and creates a new {@link EquipmentBreakdown} association with the given breakdown. Both the equipment
     * and the breakdown must exist in the database.
     *
     * @param equipmentId the ID of the equipment to report as broken down
     * @param breakdownId the ID of the breakdown being reported
     * @return the created {@link EquipmentBreakdown} entity
     * @throws EquipmentNotFoundException if the equipment with the specified ID is not found
     * @throws BreakdownNotFoundException if the breakdown with the specified ID is not found
     */
    public EquipmentBreakdown reportBreakdown(Long equipmentId, Long breakdownId){
        var equipment = equipmentRepository.findById(equipmentId).orElseThrow(EquipmentNotFoundException::new);
        var breakdown = breakdownRepository.findById(breakdownId).orElseThrow(BreakdownNotFoundException::new);
        equipment.setStatus(EquipmentStatus.BROKEN_DOWN);
        equipmentRepository.save(equipment);
        var report = new EquipmentBreakdown(new EquipmentBreakdownKey(equipment.getId(), breakdown.getId()), equipment, breakdown, null);
        return equipmentBreakdownRepository.save(report);
    }
}
