package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.UserDto;
import com.itsupport.dto.UserUpdateDto;
import com.itsupport.model.Technician;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-15T01:35:40+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class TechnicianMapperImpl implements TechnicianMapper {

    @Override
    public Technician toEntity(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        Technician technician = new Technician();

        technician.setFullName( registerRequest.getFullName() );
        technician.setMail( registerRequest.getMail() );
        technician.setUsername( registerRequest.getUsername() );
        technician.setPassword( registerRequest.getPassword() );
        technician.setPhone( registerRequest.getPhone() );
        technician.setAddress( registerRequest.getAddress() );

        return technician;
    }

    @Override
    public UserDto toDto(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( technician.getId() );
        userDto.setFullName( technician.getFullName() );
        userDto.setMail( technician.getMail() );
        userDto.setUsername( technician.getUsername() );
        userDto.setPhone( technician.getPhone() );
        userDto.setAddress( technician.getAddress() );
        userDto.setAvatarUrl( technician.getAvatarUrl() );
        userDto.setRole( technician.getRole() );

        return userDto;
    }

    @Override
    public Technician partialUpdate(UserUpdateDto userUpdateDto, Technician technician) {
        if ( userUpdateDto == null ) {
            return technician;
        }

        technician.setFullName( userUpdateDto.getFullName() );
        technician.setMail( userUpdateDto.getMail() );
        technician.setUsername( userUpdateDto.getUsername() );
        technician.setPhone( userUpdateDto.getPhone() );
        technician.setAddress( userUpdateDto.getAddress() );

        return technician;
    }
}
