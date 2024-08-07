package com.itsupport.mapper;

import com.itsupport.auth.model.RegisterRequest;
import com.itsupport.dto.*;
import com.itsupport.model.Admin;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Admin entities and various DTOs.
 *
 * This interface uses MapStruct to automate the mapping between the Admin entity
 * and its corresponding DTOs. It also provides a method for partially updating
 * an Admin entity with data from a UserUpdateDto.
 *
 * Mapping methods:
 *
 * - {@link #toEntity(RegisterRequest)}: Converts a RegisterRequest object to an Admin entity.
 * - {@link #toDto(Admin)}: Converts an Admin entity to a UserDto.
 * - {@link #partialUpdate(UserUpdateDto, Admin)}: Partially updates an Admin entity with
 *   data from a UserUpdateDto. Only the fields present in the UserUpdateDto will be updated.
 *
 * Example usage:
 *
 * {@code
 * Admin admin = adminMapper.toEntity(registerRequest);
 * UserDto userDto = adminMapper.toDto(admin);
 * adminMapper.partialUpdate(userUpdateDto, existingAdmin);
 * }
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Admin
 * @see com.itsupport.dto.UserDto
 * @see com.itsupport.dto.UserUpdateDto
 * @see com.itsupport.auth.model.RegisterRequest
 */
@Mapper(componentModel = "spring")
public interface AdminMapper {

    /**
     * Converts a RegisterRequest object to an Admin entity.
     *
     * @param registerRequest the RegisterRequest object to convert
     * @return the corresponding Admin entity
     */
    Admin toEntity(RegisterRequest registerRequest);

    /**
     * Converts an Admin entity to a UserDto.
     *
     * @param admin the Admin entity to convert
     * @return the corresponding UserDto
     */
    UserDto toDto(Admin admin);

    /**
     * Partially updates an Admin entity with data from a UserUpdateDto.
     *
     * @param userUpdateDto the UserUpdateDto containing update data
     * @param admin the Admin entity to update
     */
    Admin partialUpdate(UserUpdateDto userUpdateDto, @MappingTarget Admin admin);
}
