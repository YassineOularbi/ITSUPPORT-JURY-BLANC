package com.itsupport;

import com.itsupport.dto.TicketDto;
import com.itsupport.enums.EquipmentStatus;
import com.itsupport.enums.TicketStatus;
import com.itsupport.exception.*;
import com.itsupport.mapper.TicketMapper;
import com.itsupport.model.*;
import com.itsupport.repository.*;
import com.itsupport.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link TicketService}.
 *
 * This class contains unit tests for the {@link TicketService} class, specifically focusing on the methods related to
 * ticket management. The tests cover retrieving tickets, assigning tickets to technicians, and updating ticket statuses.
 *
 * Dependencies:
 *
 * - {@link TicketRepository} - Repository for managing {@link Ticket} entities.
 * - {@link TechnicianRepository} - Repository for managing {@link Technician} entities.
 * - {@link EquipmentRepository} - Repository for managing {@link Equipment} entities.
 * - {@link ClientRepository} - Repository for managing {@link Client} entities.
 * - {@link TicketMapper} - Mapper used for converting between {@link TicketDto} and {@link Ticket}.
 *
 * Test Methods:
 *
 * - {@link #getAllTickets_ShouldReturnListOfTickets()} - Tests retrieval of all tickets and verifies that a non-null and non-empty list is returned.
 * - {@link #getAllTickets_ShouldThrowExceptionWhenNoTicketsFound()} - Tests retrieval when no tickets are found and verifies that a {@link TicketNotFoundException} is thrown.
 * - {@link #getPendingTickets_ShouldReturnListOfPendingTickets()} - Tests retrieval of all pending tickets and verifies that a non-null and non-empty list is returned.
 * - {@link #getPendingTickets_ShouldThrowExceptionWhenNoPendingTicketsFound()} - Tests retrieval of pending tickets when none are found and verifies that a {@link TicketNotFoundException} is thrown.
 * - {@link #assignTicketToTechnician_ShouldAssignTicketAndUpdateTechnician()} - Tests assigning a ticket to a technician, updating the ticket status, and the technician’s availability.
 * - {@link #repairingTicket_ShouldUpdateTicketStatus()} - Tests updating the status of a ticket to "repairing" and verifies the correct status is set.
 */
@Transactional
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TechnicianRepository technicianRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TicketMapper ticketMapper;

    @InjectMocks
    private TicketService ticketService;

    /**
     * Initializes the test environment, including mock objects and test data setup.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests retrieval of all tickets.
     * Verifies that a non-null and non-empty list is returned when tickets are present.
     */
    @Test
    void getAllTickets_ShouldReturnListOfTickets() {
        Ticket ticket = new Ticket();
        when(ticketRepository.findAll()).thenReturn(Collections.singletonList(ticket));

        List<Ticket> tickets = ticketService.getAllTickets();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
        verify(ticketRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of all tickets when no tickets are found.
     * Verifies that a {@link TicketNotFoundException} is thrown.
     */
    @Test
    void getAllTickets_ShouldThrowExceptionWhenNoTicketsFound() {
        when(ticketRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(TicketNotFoundException.class, () -> ticketService.getAllTickets());
        verify(ticketRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of pending tickets.
     * Verifies that a non-null and non-empty list is returned when pending tickets are present.
     */
    @Test
    void getPendingTickets_ShouldReturnListOfPendingTickets() {
        Ticket ticket = new Ticket();
        when(ticketRepository.findTicketsByStatus(TicketStatus.PENDING)).thenReturn(Collections.singletonList(ticket));

        List<Ticket> tickets = ticketService.getPendingTickets();

        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
        verify(ticketRepository, times(1)).findTicketsByStatus(TicketStatus.PENDING);
    }

    /**
     * Tests retrieval of pending tickets when no pending tickets are found.
     * Verifies that a {@link TicketNotFoundException} is thrown.
     */
    @Test
    void getPendingTickets_ShouldThrowExceptionWhenNoPendingTicketsFound() {
        when(ticketRepository.findTicketsByStatus(TicketStatus.PENDING)).thenReturn(Collections.emptyList());

        assertThrows(TicketNotFoundException.class, () -> ticketService.getPendingTickets());
        verify(ticketRepository, times(1)).findTicketsByStatus(TicketStatus.PENDING);
    }

    /**
     * Tests assigning a ticket to a technician.
     * Verifies that the ticket status is updated to "processing", the technician is assigned, and the technician’s availability is set to false.
     */
    @Test
    void assignTicketToTechnician_ShouldAssignTicketAndUpdateTechnician() {
        Ticket ticket = new Ticket();
        Technician technician = new Technician();
        Long ticketId = 1L;
        Long technicianId = 2L;

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(technicianRepository.findById(technicianId)).thenReturn(Optional.of(technician));
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(technicianRepository.save(technician)).thenReturn(technician);

        Ticket updatedTicket = ticketService.assignTicketToTechnician(ticketId, technicianId);

        assertNotNull(updatedTicket);
        assertEquals(TicketStatus.PROCESSING, updatedTicket.getStatus());
        assertEquals(technician, updatedTicket.getTechnician());
        assertFalse(technician.getAvailability());
        verify(ticketRepository, times(1)).findById(ticketId);
        verify(technicianRepository, times(1)).findById(technicianId);
        verify(ticketRepository, times(1)).save(ticket);
        verify(technicianRepository, times(1)).save(technician);
    }

    /**
     * Tests updating a ticket’s status to "repairing".
     * Verifies that the status of the ticket is updated correctly.
     */
    @Test
    void repairingTicket_ShouldUpdateTicketStatus() {
        Ticket ticket = new Ticket();
        Long ticketId = 1L;

        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket updatedTicket = ticketService.repairingTicket(ticketId);

        assertNotNull(updatedTicket);
        assertEquals(TicketStatus.REPAIRING, updatedTicket.getStatus());
        verify(ticketRepository, times(1)).findById(ticketId);
        verify(ticketRepository, times(1)).save(ticket);
    }

}
