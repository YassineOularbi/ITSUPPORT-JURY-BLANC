package com.itsupport.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) for updating user information.
 *
 * This class is used to encapsulate the data required for updating a user's details in the system.
 * It includes fields for the user's full name, email address, and username.
 *
 * <p>Example usage:</p>
 * <pre>
 * UserUpdateDto userUpdateDto = new UserUpdateDto();
 * userUpdateDto.setFullName("John Doe");
 * userUpdateDto.setMail("john.doe@example.com");
 * userUpdateDto.setUsername("johndoe");
 * </pre>
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    private String fullName;
    private String mail;
    private String username;
    private String phone;
    private String address;
    private MultipartFile picture;
}
