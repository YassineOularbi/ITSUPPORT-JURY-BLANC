package com.itsupport.dto;

import lombok.*;

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
 * @see com.itsupport.controller.AdminController
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    /**
     * Full name of the user.
     * This field represents the user's full name, which will be used for updating their profile information.
     *
     * Example: "John Doe", "Jane Smith"
     */
    private String fullName;

    /**
     * Email address of the user.
     * This field contains the user's email address, which will be used for updating their contact information.
     *
     * Example: "john.doe@example.com", "jane.smith@example.com"
     */
    private String mail;

    /**
     * Username of the user.
     * This field represents the user's login username, which will be used for updating their account information.
     *
     * Example: "johndoe", "janesmith"
     */
    private String username;
}
