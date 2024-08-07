package com.itsupport.mapper;

import com.itsupport.dto.UserDto;
import com.itsupport.model.User;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between User entities and UserDto.
 *
 * This interface uses MapStruct to automate the mapping between the User entity
 * and the UserDto. It provides a method for converting a User entity to a UserDto.
 *
 * Mapping methods:
 *
 * - {@link #toDto(User)}: Converts a User entity to a UserDto object.
 *
 * Example usage:
 *
 * {@code
 * UserDto userDto = userMapper.toDto(user);
 * }
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.User
 * @see com.itsupport.dto.UserDto
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Converts a User entity to a UserDto object.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDto object
     */
    UserDto toDto(User user);
}
