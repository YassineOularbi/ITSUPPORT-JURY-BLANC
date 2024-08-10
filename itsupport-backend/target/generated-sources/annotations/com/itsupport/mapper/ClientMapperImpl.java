package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.UserDto;
import com.itsupport.dto.UserUpdateDto;
import com.itsupport.model.Client;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-10T18:46:53+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        Client client = new Client();

        client.setFullName( registerRequest.getFullName() );
        client.setMail( registerRequest.getMail() );
        client.setUsername( registerRequest.getUsername() );
        client.setPassword( registerRequest.getPassword() );

        return client;
    }

    @Override
    public UserDto toDto(Client client) {
        if ( client == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( client.getId() );
        userDto.setFullName( client.getFullName() );
        userDto.setMail( client.getMail() );
        userDto.setUsername( client.getUsername() );
        userDto.setRole( client.getRole() );

        return userDto;
    }

    @Override
    public Client partialUpdate(UserUpdateDto userUpdateDto, Client client) {
        if ( userUpdateDto == null ) {
            return client;
        }

        client.setFullName( userUpdateDto.getFullName() );
        client.setMail( userUpdateDto.getMail() );
        client.setUsername( userUpdateDto.getUsername() );

        return client;
    }
}
