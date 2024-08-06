package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.UserDto;
import com.itsupport.dto.UserUpdateDto;
import com.itsupport.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TechnicianMapper {
    Technician toEntity(RegisterRequest registerRequest);
    UserDto toDto(Technician technician);
    Technician partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget Technician technician);
}
