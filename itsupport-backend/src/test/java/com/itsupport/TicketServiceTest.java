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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

    @Test
    void getAllTickets_ShouldThrowExceptionWhenNoTicketsFound() {
        when(ticketRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(TicketNotFoundException.class, () -> ticketService.getAllTickets());
        verify(ticketRepository, times(1)).findAll();
    }

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

    @Test
    void getPendingTickets_ShouldThrowExceptionWhenNoPendingTicketsFound() {
        when(ticketRepository.findTicketsByStatus(TicketStatus.PENDING)).thenReturn(Collections.emptyList());

        assertThrows(TicketNotFoundException.class, () -> ticketService.getPendingTickets());
        verify(ticketRepository, times(1)).findTicketsByStatus(TicketStatus.PENDING);
    }


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
