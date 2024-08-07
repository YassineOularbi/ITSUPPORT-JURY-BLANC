package com.itsupport.repository;

import com.itsupport.enums.TicketStatus;
import com.itsupport.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Ticket} entities.
 *
 * The TicketRepository interface extends JpaRepository, providing a set of CRUD operations and custom query methods
 * for the {@link Ticket} entity. This interface enables interaction with the "ticket" table in the database
 * and includes methods for querying tickets based on various criteria, such as status, technician, and client.
 *
 * Generic Parameters:
 *
 * - {@link Ticket} - The type of the entity managed by the repository.
 * - {@link Long} - The type of the entity's primary key.
 *
 * Custom Methods:
 *
 * - {@link #findTicketsByStatus(TicketStatus)} - Retrieves a list of {@link Ticket} entities with a specific status.
 * - {@link #findTicketsByStatusAndTechnician_Id(TicketStatus, Long)} - Retrieves a list of {@link Ticket} entities with a specific status and assigned to a particular technician.
 * - {@link #findTicketsByClient_Id(Long)} - Retrieves a list of {@link Ticket} entities associated with a specific client ID.
 * - {@link #findTicketsByTechnician_Id(Long)} - Retrieves a list of {@link Ticket} entities assigned to a specific technician.
 *
 * Methods inherited from JpaRepository:
 *
 * - {@link JpaRepository#findAll()} - Retrieves all entities.
 * - {@link JpaRepository@findById(Long)} - Retrieves an entity by its ID.
 * - {@link JpaRepository@save(Ticket)} - Saves or updates an entity.
 * - {@link JpaRepository@deleteById(Long)} - Deletes an entity by its ID.
 * - {@link JpaRepository#count()} - Counts the total number of entities.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Ticket
 * @see com.itsupport.enums.TicketStatus
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * Retrieves a list of {@link Ticket} entities with a specific status.
     *
     * @param status the status of the tickets to be retrieved
     * @return a list of {@link Ticket} entities with the specified status
     */
    List<Ticket> findTicketsByStatus(TicketStatus status);

    /**
     * Retrieves a list of {@link Ticket} entities with a specific status and assigned to a particular technician.
     *
     * @param status the status of the tickets to be retrieved
     * @param technician_id the ID of the technician to whom the tickets are assigned
     * @return a list of {@link Ticket} entities with the specified status and assigned to the given technician
     */
    List<Ticket> findTicketsByStatusAndTechnician_Id(TicketStatus status, Long technician_id);

    /**
     * Retrieves a list of {@link Ticket} entities associated with a specific client ID.
     *
     * @param client_id the ID of the client for which tickets are to be retrieved
     * @return a list of {@link Ticket} entities associated with the specified client ID
     */
    List<Ticket> findTicketsByClient_Id(Long client_id);

    /**
     * Retrieves a list of {@link Ticket} entities assigned to a specific technician.
     *
     * @param technician_id the ID of the technician to whom the tickets are assigned
     * @return a list of {@link Ticket} entities assigned to the specified technician
     */
    List<Ticket> findTicketsByTechnician_Id(Long technician_id);
}
