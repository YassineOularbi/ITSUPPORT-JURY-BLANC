package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.UserDto;
import com.itsupport.dto.UserUpdateDto;
import com.itsupport.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(RegisterRequest registerRequest);
    UserDto toDto(Client client);
    Client partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget Client client);
}
