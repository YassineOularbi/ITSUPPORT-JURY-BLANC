package com.itsupport.repository;

import com.itsupport.enums.TicketStatus;
import com.itsupport.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketsByStatus(TicketStatus status);
    List<Ticket> findTicketsByStatusAndTechnician_Id(TicketStatus status, Long technician_id);
}
