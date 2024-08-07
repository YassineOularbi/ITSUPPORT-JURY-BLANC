package com.itsupport.repository;

import com.itsupport.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Technician} entities.
 *
 * The TechnicianRepository interface extends JpaRepository, providing a set of CRUD operations and custom query methods
 * for the {@link Technician} entity. This interface facilitates interaction with the "technician" table in the database
 * and includes methods for querying technicians based on their availability.
 *
 * Generic Parameters:
 *
 * - {@link Technician} - The type of the entity managed by the repository.
 * - {@link Long} - The type of the entity's primary key.
 *
 * Custom Methods:
 *
 * - {@link #findTechniciansByAvailabilityIsTrue()} - Retrieves a list of {@link Technician} entities who are currently available.
 *
 * Methods inherited from JpaRepository:
 *
 * - {@link JpaRepository#findAll()} - Retrieves all entities.
 * - {@link JpaRepository@findById(Long)} - Retrieves an entity by its ID.
 * - {@link JpaRepository@save(Technician)} - Saves or updates an entity.
 * - {@link JpaRepository@deleteById(Long)} - Deletes an entity by its ID.
 * - {@link JpaRepository@count()} - Counts the total number of entities.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Technician
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    /**
     * Retrieves a list of {@link Technician} entities who are currently available.
     *
     * @return a list of {@link Technician} entities with availability set to true
     */
    List<Technician> findTechniciansByAvailabilityIsTrue();
}
