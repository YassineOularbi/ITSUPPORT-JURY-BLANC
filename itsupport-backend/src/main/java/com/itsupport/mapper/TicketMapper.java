package com.itsupport.mapper;

import com.itsupport.dto.TicketDto;
import com.itsupport.model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toEntity (TicketDto ticketDto);
}
