package com.itsupport.repository;

import com.itsupport.enums.EquipmentStatus;
import com.itsupport.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Equipment} entities.
 *
 * The EquipmentRepository interface extends JpaRepository, providing a set of CRUD operations and custom query methods
 * for the {@link Equipment} entity. This interface enables interaction with the "equipment" table in the database
 * and includes methods for querying equipment based on their status and associated client.
 *
 * Generic Parameters:
 *
 * - {@link Equipment} - The type of the entity managed by the repository.
 * - {@link Long} - The type of the entity's primary key.
 *
 * Custom Methods:
 *
 * - {@link #getEquipmentByStatus(EquipmentStatus)} - Retrieves a list of {@link Equipment} entities based on their status.
 * - {@link #getEquipmentByClientId(Long)} - Retrieves a list of {@link Equipment} entities associated with a specific client ID.
 *
 * Methods inherited from JpaRepository:
 *
 * - {@link JpaRepository#findAll()} - Retrieves all entities.
 * - {@link JpaRepository@findById(Long)} - Retrieves an entity by its ID.
 * - {@link JpaRepository@save(Equipment)} - Saves or updates an entity.
 * - {@link JpaRepository@deleteById(Long)} - Deletes an entity by its ID.
 * - {@link JpaRepository#count()} - Counts the total number of entities.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.enums.EquipmentStatus
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    /**
     * Retrieves a list of {@link Equipment} entities based on their status.
     *
     * @param status the status of the equipment to be retrieved
     * @return a list of {@link Equipment} entities with the specified status
     */
    List<Equipment> getEquipmentByStatus(EquipmentStatus status);

    /**
     * Retrieves a list of {@link Equipment} entities associated with a specific client ID.
     *
     * @param id the ID of the client for which equipment is to be retrieved
     * @return a list of {@link Equipment} entities associated with the specified client ID
     */
    List<Equipment> getEquipmentByClientId(Long id);
}
