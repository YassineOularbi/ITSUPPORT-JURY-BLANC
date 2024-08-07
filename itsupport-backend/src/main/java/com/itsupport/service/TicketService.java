package com.itsupport.service;

import com.itsupport.dto.TicketDto;
import com.itsupport.enums.EquipmentStatus;
import com.itsupport.enums.TicketStatus;
import com.itsupport.exception.EquipmentNotFoundException;
import com.itsupport.exception.TechnicianNotFoundException;
import com.itsupport.exception.TicketNotFoundException;
import com.itsupport.mapper.TicketMapper;
import com.itsupport.model.EquipmentBreakdown;
import com.itsupport.model.Ticket;
import com.itsupport.repository.EquipmentRepository;
import com.itsupport.repository.TechnicianRepository;
import com.itsupport.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final TechnicianRepository technicianRepository;

    private final EquipmentRepository equipmentRepository;

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

    public List<Ticket> getTicketsByClient(Long id){
        var tickets = ticketRepository.findTicketsByClient_Id(id);
        if (tickets.isEmpty()){
            throw new TicketNotFoundException();
        }
        return tickets;
    }

    public List<Ticket> getTicketsByTechnician(Long id){
        var tickets = ticketRepository.findTicketsByTechnician_Id(id);
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

    public Ticket repairingTicket(Long id){
        var ticket = ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        ticket.setStatus(TicketStatus.REPAIRING);
        return ticketRepository.save(ticket);
    }

    public Ticket repairedTicket(Long id){
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

    public Ticket failedTicket(Long id){
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
