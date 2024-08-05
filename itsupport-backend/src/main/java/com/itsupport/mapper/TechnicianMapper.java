package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.UserDto;
import com.itsupport.model.Technician;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicianMapper {
    Technician toEntity(RegisterRequest registerRequest);
    UserDto toDto(Technician technician);
}
