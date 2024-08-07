package com.itsupport.service;

import com.itsupport.dto.TicketDto;
import com.itsupport.enums.TicketStatus;
import com.itsupport.exception.TechnicianNotFoundException;
import com.itsupport.exception.TicketNotFoundException;
import com.itsupport.mapper.TicketMapper;
import com.itsupport.model.EquipmentBreakdown;
import com.itsupport.model.Ticket;
import com.itsupport.repository.TechnicianRepository;
import com.itsupport.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final TechnicianRepository technicianRepository;

    private final TicketMapper ticketMapper;

    public List<Ticket> getAllTickets(){
        var tickets = ticketRepository.findAll();
        if (tickets.isEmpty()){
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    public List<Ticket> getPendingTickets(){
        var tickets = ticketRepository.findTicketsByStatus(TicketStatus.PENDING);
        if (tickets.isEmpty()){
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    public List<Ticket> getProcessingTickets(Long id){
        var tickets = ticketRepository.findTicketsByStatusAndTechnician_Id(TicketStatus.PROCESSING, id);
        if (tickets.isEmpty()){
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    public Ticket createTicket(EquipmentBreakdown equipmentBreakdown, TicketDto ticketDto){
        var ticket = ticketMapper.toEntity(ticketDto);
        ticket.setStatus(TicketStatus.PENDING);
        ticket.setEquipmentBreakdown(equipmentBreakdown);
        ticket.setClient(equipmentBreakdown.getEquipment().getClient());
        return ticketRepository.save(ticket);
    }

    public Ticket assignTicketToTechnician(Long ticketId, Long technicianId){
        var ticket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        var technician = technicianRepository.findById(technicianId).orElseThrow(TechnicianNotFoundException::new);
        technician.setAvailability(false);
        ticket.setTechnician(technician);
        ticket.setStatus(TicketStatus.PROCESSING);
        technicianRepository.save(technician);
        return ticketRepository.save(ticket);
    }
}
