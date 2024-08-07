package com.itsupport.repository;

import com.itsupport.model.Breakdown;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Breakdown} entities.
 *
 * The BreakdownRepository interface extends JpaRepository, providing a set of CRUD operations and custom query methods
 * for the {@link Breakdown} entity. This interface facilitates interaction with the "breakdown" table in the database.
 *
 * Generic Parameters:
 *
 * - {@link Breakdown} - The type of the entity managed by the repository.
 * - {@link Long} - The type of the entity's primary key.
 *
 * Methods inherited from JpaRepository:
 *
 * - {@link JpaRepository#findAll()} - Retrieves all entities.
 * - {@link JpaRepository@findById(Long)} - Retrieves an entity by its ID.
 * - {@link JpaRepository@save(Breakdown)} - Saves or updates an entity.
 * - {@link JpaRepository@deleteById(Long)} - Deletes an entity by its ID.
 * - {@link JpaRepository#count()} - Counts the total number of entities.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Breakdown
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {
}
