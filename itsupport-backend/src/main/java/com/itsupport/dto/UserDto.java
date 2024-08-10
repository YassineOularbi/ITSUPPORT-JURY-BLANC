package com.itsupport.dto;

import com.itsupport.enums.Role;
import lombok.*;

/**
 * Data Transfer Object (DTO) for representing a user.
 *
 * This class is used to encapsulate user-related data for transferring between different layers of the application.
 * It includes essential information such as the user's identifier, name, email, username, and role.
 *
 * <p>Example usage:</p>
 * <pre>
 * UserDto userDto = new UserDto();
 * userDto.setId(1L);
 * userDto.setFullName("John Doe");
 * userDto.setMail("john.doe@example.com");
 * userDto.setUsername("johndoe");
 * userDto.setRole(Role.ADMIN);
 * </pre>
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    /**
     * Unique identifier of the user.
     * This field represents the user's ID and is used for identifying the user in various operations.
     *
     * Example: 1L, 42L
     */
    private Long id;

    /**
     * Full name of the user.
     * This field contains the user's full name as used in the system.
     *
     * Example: "John Doe", "Jane Smith"
     */
    private String fullName;

    /**
     * Email address of the user.
     * This field stores the user's email address, which can be used for communication and notifications.
     *
     * Example: "john.doe@example.com", "jane.smith@example.com"
     */
    private String mail;

    /**
     * Username of the user.
     * This field contains the user's login username, which is used for authentication and identification.
     *
     * Example: "johndoe", "janesmith"
     */
    private String username;

    /**
     * Role of the user in the system.
     * This field indicates the user's role, such as ADMIN, TECHNICIAN, or CLIENT, which determines their permissions and access levels.
     *
     * @see com.itsupport.enums.Role
     * Example: Role.ADMIN, Role.TECHNICIAN
     */
    private Role role;
}
