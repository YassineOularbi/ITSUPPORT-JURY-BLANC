package com.itsupport.repository;

import com.itsupport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 *
 * The UserRepository interface extends JpaRepository, providing a set of CRUD operations and a custom query method
 * for the {@link User} entity. This interface allows interaction with the "user" table in the database and includes
 * a method to find users by their username.
 *
 * Generic Parameters:
 *
 * - {@link User} - The type of the entity managed by the repository.
 * - {@link Long} - The type of the entity's primary key.
 *
 * Custom Methods:
 *
 * - {@link #findByUsername(String)} - Retrieves an {@link User} entity based on the provided username.
 *
 * Methods inherited from JpaRepository:
 *
 * - {@link JpaRepository#findAll()} - Retrieves all entities.
 * - {@link JpaRepository@findById(Long)} - Retrieves an entity by its ID.
 * - {@link JpaRepository@save(User)} - Saves or updates an entity.
 * - {@link JpaRepository@deleteById(Long)} - Deletes an entity by its ID.
 * - {@link JpaRepository#count()} - Counts the total number of entities.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.User
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves an {@link User} entity based on the provided username.
     *
     * @param username the username of the user to be retrieved
     * @return an {@link Optional} containing the {@link User} entity if found, or an empty {@link Optional} if not found
     */
    Optional<User> findByUsername(String username);
}
