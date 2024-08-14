package com.itsupport.dto;

import com.itsupport.enums.Role;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private Long id;
    private String fullName;
    private String mail;
    private String username;
    private String phone;
    private String address;
    private String avatarUrl;
    private Role role;
}
