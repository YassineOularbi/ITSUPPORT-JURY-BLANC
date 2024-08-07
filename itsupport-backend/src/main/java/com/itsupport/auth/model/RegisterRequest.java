package com.itsupport.auth.model;

import lombok.*;

/**
 * Represents a registration request containing user details for account creation.
 *
 * This class is used to encapsulate the necessary information for registering a new
 * user account. It includes personal details such as full name, email, username,
 * and password.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /**
     * The full name of the user registering for an account.
     *
     * This field is used to store the complete name of the user and is required
     * for creating a new user account.
     */
    private String fullName;

    /**
     * The email address of the user registering for an account.
     *
     * This field is used for communication and account verification purposes.
     * It must be a valid email format and is required for registration.
     */
    private String mail;

    /**
     * The username chosen by the user for their account.
     *
     * This field is used as a unique identifier for the user in the system.
     * It must be unique and is required for registration.
     */
    private String username;

    /**
     * The password chosen by the user for their account.
     *
     * This field is used to authenticate the user and must be kept secure.
     * It is required for registration and should be hashed before storage.
     */
    private String password;
}
