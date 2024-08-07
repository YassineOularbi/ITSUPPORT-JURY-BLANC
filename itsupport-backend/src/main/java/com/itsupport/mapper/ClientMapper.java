package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.*;
import com.itsupport.model.Client;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Client entities and ClientDto.
 *
 * This interface uses MapStruct to automate the mapping between the Client entity
 * and the ClientDto. It also provides methods for converting a RegisterRequest to a Client
 * entity and for partially updating a Client entity with data from a UserUpdateDto.
 *
 * Mapping methods:
 *
 * - {@link #toEntity(RegisterRequest)}: Converts a RegisterRequest object to a Client entity.
 * - {@link #toDto(Client)}: Converts a Client entity to a UserDto object.
 * - {@link #partialUpdate(UserUpdateDto, Client)}: Partially updates a Client entity with
 *   data from a UserUpdateDto. Only the fields present in the UserUpdateDto will be updated.
 *
 * Example usage:
 *
 * {@code
 * Client client = clientMapper.toEntity(registerRequest);
 * UserDto userDto = clientMapper.toDto(client);
 * clientMapper.partialUpdate(userUpdateDto, existingClient);
 * }
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Client
 * @see com.itsupport.dto.UserDto
 * @see com.itsupport.auth.model.RegisterRequest
 * @see com.itsupport.dto.UserUpdateDto
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    /**
     * Converts a RegisterRequest object to a Client entity.
     *
     * @param registerRequest the RegisterRequest object to convert
     * @return the corresponding Client entity
     */
    Client toEntity(RegisterRequest registerRequest);

    /**
     * Converts a Client entity to a UserDto object.
     *
     * @param client the Client entity to convert
     * @return the corresponding UserDto object
     */
    UserDto toDto(Client client);

    /**
     * Partially updates a Client entity with data from a UserUpdateDto.
     *
     * @param userUpdateDto the UserUpdateDto containing update data
     * @param client the Client entity to update
     * @return the updated Client entity
     */
    Client partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget Client client);
}
