package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.*;
import com.itsupport.model.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(RegisterRequest registerRequest);
    UserDto toDto(Client client);
    Client partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget Client client);
}
