package com.itsupport.service;

import com.itsupport.dto.TicketDto;
import com.itsupport.enums.*;
import com.itsupport.exception.*;
import com.itsupport.mapper.TicketMapper;
import com.itsupport.model.*;
import com.itsupport.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing {@link Ticket} entities.
 *
 * The TicketService class provides business logic and operations related to {@link Ticket} entities.
 * It interacts with {@link TicketRepository}, {@link TechnicianRepository}, {@link EquipmentRepository},
 * and {@link ClientRepository} for data access, and {@link TicketMapper} for mapping {@link TicketDto}
 * to {@link Ticket}. This service handles ticket creation, updating, and retrieval operations.
 *
 * Dependencies:
 *
 * - {@link TicketRepository} - Repository for CRUD operations on {@link Ticket} entities.
 * - {@link TechnicianRepository} - Repository for CRUD operations on {@link Technician} entities.
 * - {@link EquipmentRepository} - Repository for CRUD operations on {@link Equipment} entities.
 * - {@link ClientRepository} - Repository for CRUD operations on {@link Client} entities.
 * - {@link TicketMapper} - Mapper for converting {@link TicketDto} to {@link Ticket} and vice versa.
 *
 * Methods:
 *
 * - {@link #getAllTickets()} - Retrieves a list of all {@link Ticket} entities. Throws {@link TicketNotFoundException}
 *   if no tickets are found.
 * - {@link #getPendingTickets()} - Retrieves a list of {@link Ticket} entities with the status {@link TicketStatus#PENDING}.
 *   Throws {@link TicketNotFoundException} if no pending tickets are found.
 * - {@link #getProcessingTickets(Long)} - Retrieves a list of {@link Ticket} entities with the status {@link TicketStatus#PROCESSING}
 *   for a specific technician. Throws {@link TicketNotFoundException} if no processing tickets are found for the technician.
 * - {@link #getTicketsByClient(Long)} - Retrieves a list of {@link Ticket} entities associated with a specific client.
 *   Throws {@link TicketNotFoundException} if no tickets are found for the client.
 * - {@link #getTicketsByTechnician(Long)} - Retrieves a list of {@link Ticket} entities assigned to a specific technician.
 *   Throws {@link TicketNotFoundException} if no tickets are found for the technician.
 * - {@link #createTicket(EquipmentBreakdown, TicketDto)} - Creates a new {@link Ticket} based on the provided {@link TicketDto}
 *   and {@link EquipmentBreakdown}. Sets the status to {@link TicketStatus#PENDING} and associates the ticket with a client.
 * - {@link #assignTicketToTechnician(Long, Long)} - Assigns a {@link Ticket} to a {@link Technician}, updates the technician's availability
 *   to false, and sets the ticket status to {@link TicketStatus#PROCESSING}.
 * - {@link #repairingTicket(Long)} - Updates the status of a {@link Ticket} to {@link TicketStatus#REPAIRING}.
 * - {@link #repairedTicket(Long)} - Updates the status of a {@link Ticket} to {@link TicketStatus#REPAIRED}, sets the resolution date,
 *   and updates the associated {@link Equipment} and {@link Technician} statuses.
 * - {@link #failedTicket(Long)} - Updates the status of a {@link Ticket} to {@link TicketStatus#FAILED}, sets the {@link Equipment} status
 *   to {@link EquipmentStatus#OUT_OF_SERVICE}, and updates the associated {@link Technician} status.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Ticket
 * @see com.itsupport.dto.TicketDto
 * @see com.itsupport.mapper.TicketMapper
 * @see com.itsupport.repository.TicketRepository
 * @see com.itsupport.repository.TechnicianRepository
 * @see com.itsupport.repository.EquipmentRepository
 * @see com.itsupport.repository.ClientRepository
 * @see com.itsupport.exception.TicketNotFoundException
 * @see com.itsupport.exception.TechnicianNotFoundException
 * @see com.itsupport.exception.EquipmentNotFoundException
 * @see com.itsupport.exception.ClientNotFoundException
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TechnicianRepository technicianRepository;
    private final EquipmentRepository equipmentRepository;
    private final ClientRepository clientRepository;
    private final TicketMapper ticketMapper;

    /**
     * Retrieves a list of all {@link Ticket} entities.
     *
     * @return a list of all {@link Ticket} entities
     * @throws TicketNotFoundException if no tickets are found in the database
     */
    public List<Ticket> getAllTickets() {
        var tickets = ticketRepository.findAll();
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    /**
     * Retrieves a list of {@link Ticket} entities with the status {@link TicketStatus#PENDING}.
     *
     * @return a list of pending {@link Ticket} entities
     * @throws TicketNotFoundException if no pending tickets are found
     */
    public List<Ticket> getPendingTickets() {
        var tickets = ticketRepository.findTicketsByStatus(TicketStatus.PENDING);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    /**
     * Retrieves a list of {@link Ticket} entities with the status {@link TicketStatus#PROCESSING} for a specific technician.
     *
     * @param id the ID of the technician
     * @return a list of {@link Ticket} entities with the status {@link TicketStatus#PROCESSING} for the technician
     * @throws TicketNotFoundException if no processing tickets are found for the technician
     */
    public List<Ticket> getProcessingTickets(Long id) {
        var tickets = ticketRepository.findTicketsByStatusAndTechnician_Id(TicketStatus.PROCESSING, id);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    /**
     * Retrieves a list of {@link Ticket} entities associated with a specific client.
     *
     * @param id the ID of the client
     * @return a list of {@link Ticket} entities associated with the client
     * @throws TicketNotFoundException if no tickets are found for the client
     */
    public List<Ticket> getTicketsByClient(Long id) {
        var tickets = ticketRepository.findTicketsByClient_Id(id);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    /**
     * Retrieves a list of {@link Ticket} entities assigned to a specific technician.
     *
     * @param id the ID of the technician
     * @return a list of {@link Ticket} entities assigned to the technician
     * @throws TicketNotFoundException if no tickets are found for the technician
     */
    public List<Ticket> getTicketsByTechnician(Long id) {
        var tickets = ticketRepository.findTicketsByTechnician_Id(id);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    /**
     * Creates a new {@link Ticket} based on the provided {@link TicketDto} and {@link EquipmentBreakdown}.
     *
     * @param equipmentBreakdown the {@link EquipmentBreakdown} associated with the ticket
     * @param ticketDto the data transfer object containing ticket information
     * @return the created {@link Ticket}
     */
    public Ticket createTicket(EquipmentBreakdown equipmentBreakdown, TicketDto ticketDto) {
        var ticket = ticketMapper.toEntity(ticketDto);
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setEquipmentBreakdown(equipmentBreakdown);
        var client = clientRepository.findById(equipmentBreakdown.getEquipment().getClient().getId()).orElseThrow(ClientNotFoundException::new);
        ticket.setClient(client);
        return ticketRepository.save(ticket);
    }

    /**
     * Assigns a {@link Ticket} to a {@link Technician}, updates the technician's availability to false, and sets the ticket status to {@link TicketStatus#PROCESSING}.
     *
     * @param ticketId the ID of the ticket to be assigned
     * @param technicianId the ID of the technician to whom the ticket will be assigned
     * @return the updated {@link Ticket}
     * @throws TicketNotFoundException if the ticket with the specified ID is not found
     * @throws TechnicianNotFoundException if the technician with the specified ID is not found
     */
    public Ticket assignTicketToTechnician(Long ticketId, Long technicianId) {
        var ticket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        var technician = technicianRepository.findById(technicianId).orElseThrow(TechnicianNotFoundException::new);
        technician.setAvailability(false);
        ticket.setTechnician(technician);
        ticket.setStatus(TicketStatus.PROCESSING);
        technicianRepository.save(technician);
        return ticketRepository.save(ticket);
    }

    /**
     * Updates the status of a {@link Ticket} to {@link TicketStatus#REPAIRING}.
     *
     * @param id the ID of the ticket to be updated
     * @return the updated {@link Ticket}
     * @throws TicketNotFoundException if the ticket with the specified ID is not found
     */
    public Ticket repairingTicket(Long id) {
        var ticket = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        ticket.setStatus(TicketStatus.REPAIRING);
        return ticketRepository.save(ticket);
    }

    /**
     * Updates the status of a {@link Ticket} to {@link TicketStatus#REPAIRED}, sets the resolution date, and updates the status of the associated {@link Equipment}
     * and {@link Technician}.
     *
     * @param id the ID of the ticket to be updated
     * @return the updated {@link Ticket}
     * @throws TicketNotFoundException if the ticket with the specified ID is not found
     * @throws EquipmentNotFoundException if the equipment associated with the ticket is not found
     * @throws TechnicianNotFoundException if the technician associated with the ticket is not found
     */
    public Ticket repairedTicket(Long id) {
        var ticket = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        ticket.setStatus(TicketStatus.REPAIRED);
        ticket.setResolutionDate(Date.valueOf(LocalDate.now()));
        var equipment = equipmentRepository.findById(ticket.getEquipmentBreakdown().getEquipment().getId()).orElseThrow(EquipmentNotFoundException::new);
        equipment.setStatus(EquipmentStatus.IN_SERVICE);
        equipmentRepository.save(equipment);
        var technician = technicianRepository.findById(ticket.getTechnician().getId()).orElseThrow(TechnicianNotFoundException::new);
        technician.setAvailability(true);
        technicianRepository.save(technician);
        return ticketRepository.save(ticket);
    }

    /**
     * Updates the status of a {@link Ticket} to {@link TicketStatus#FAILED}, sets the {@link Equipment} status to {@link EquipmentStatus#OUT_OF_SERVICE},
     * and updates the {@link Technician} status to available.
     *
     * @param id the ID of the ticket to be updated
     * @return the updated {@link Ticket}
     * @throws TicketNotFoundException if the ticket with the specified ID is not found
     * @throws EquipmentNotFoundException if the equipment associated with the ticket is not found
     * @throws TechnicianNotFoundException if the technician associated with the ticket is not found
     */
    public Ticket failedTicket(Long id) {
        var ticket = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        ticket.setStatus(TicketStatus.FAILED);
        var equipment = equipmentRepository.findById(ticket.getEquipmentBreakdown().getEquipment().getId()).orElseThrow(EquipmentNotFoundException::new);
        equipment.setStatus(EquipmentStatus.OUT_OF_SERVICE);
        equipmentRepository.save(equipment);
        var technician = technicianRepository.findById(ticket.getTechnician().getId()).orElseThrow(TechnicianNotFoundException::new);
        technician.setAvailability(true);
        technicianRepository.save(technician);
        return ticketRepository.save(ticket);
    }
}
