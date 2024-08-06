package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.UserDto;
import com.itsupport.dto.UserUpdateDto;
import com.itsupport.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toEntity (RegisterRequest registerRequest);
    UserDto toDto(Admin admin);
    Admin partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget Admin admin);
}
