package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.*;
import com.itsupport.model.Technician;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Technician entities and UserDto.
 *
 * This interface uses MapStruct to automate the mapping between the Technician entity
 * and the UserDto. It provides methods for converting a RegisterRequest to a Technician entity,
 * converting a Technician entity to a UserDto, and partially updating a Technician entity
 * with data from a UserUpdateDto.
 *
 * Mapping methods:
 *
 * - {@link #toEntity(RegisterRequest)}: Converts a RegisterRequest object to a Technician entity.
 * - {@link #toDto(Technician)}: Converts a Technician entity to a UserDto.
 * - {@link #partialUpdate(UserUpdateDto, Technician)}: Partially updates a Technician entity with
 *   data from a UserUpdateDto. Only the fields present in the UserUpdateDto will be updated.
 *
 * Example usage:
 *
 * {@code
 * Technician technician = technicianMapper.toEntity(registerRequest);
 * UserDto userDto = technicianMapper.toDto(technician);
 * technicianMapper.partialUpdate(userUpdateDto, existingTechnician);
 * }
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Technician
 * @see com.itsupport.dto.UserDto
 * @see com.itsupport.dto.UserUpdateDto
 */
@Mapper(componentModel = "spring")
public interface TechnicianMapper {

    /**
     * Converts a RegisterRequest object to a Technician entity.
     *
     * @param registerRequest the RegisterRequest object to convert
     * @return the corresponding Technician entity
     */
    Technician toEntity(RegisterRequest registerRequest);

    /**
     * Converts a Technician entity to a UserDto.
     *
     * @param technician the Technician entity to convert
     * @return the corresponding UserDto
     */
    UserDto toDto(Technician technician);

    /**
     * Partially updates a Technician entity with data from a UserUpdateDto.
     *
     * @param userUpdateDto the UserUpdateDto containing update data
     * @param technician the Technician entity to update
     * @return the updated Technician entity
     */
    Technician partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget Technician technician);
}
