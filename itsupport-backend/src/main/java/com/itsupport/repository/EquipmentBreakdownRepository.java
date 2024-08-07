package com.itsupport.repository;

import com.itsupport.model.EquipmentBreakdown;
import com.itsupport.model.EquipmentBreakdownKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link EquipmentBreakdown} entities.
 *
 * The EquipmentBreakdownRepository interface extends JpaRepository, providing a set of CRUD operations and custom query methods
 * for the {@link EquipmentBreakdown} entity. This interface allows interaction with the "equipment_breakdown" table in the database
 * and includes custom query methods specific to the needs of the application.
 *
 * Generic Parameters:
 *
 * - {@link EquipmentBreakdown} - The type of the entity managed by the repository.
 * - {@link EquipmentBreakdownKey} - The type of the composite primary key of the entity.
 *
 * Custom Methods:
 *
 * - {@link #findAllByEquipmentId(Long)} - Retrieves a list of {@link EquipmentBreakdown} entities associated with a specific equipment ID.
 *
 * Methods inherited from JpaRepository:
 *
 * - {@link JpaRepository#findAll()} - Retrieves all entities.
 * - {@link JpaRepository@findById(EquipmentBreakdownKey)} - Retrieves an entity by its composite key.
 * - {@link JpaRepository@save(EquipmentBreakdown)} - Saves or updates an entity.
 * - {@link JpaRepository@deleteById(EquipmentBreakdownKey)} - Deletes an entity by its composite key.
 * - {@link JpaRepository#count()} - Counts the total number of entities.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.EquipmentBreakdown
 * @see com.itsupport.model.EquipmentBreakdownKey
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface EquipmentBreakdownRepository extends JpaRepository<EquipmentBreakdown, EquipmentBreakdownKey> {

    /**
     * Retrieves a list of {@link EquipmentBreakdown} entities associated with a specific equipment ID.
     *
     * @param id the ID of the equipment for which breakdowns are to be retrieved
     * @return a list of {@link EquipmentBreakdown} entities associated with the specified equipment ID
     */
    List<EquipmentBreakdown> findAllByEquipmentId(Long id);
}
