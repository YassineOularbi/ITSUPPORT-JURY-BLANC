package com.itsupport.auth.model;

import com.itsupport.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

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

    private String fullName;
    private String mail;
    private String username;
    private String password;
    private String phone;
    private String address;
    private MultipartFile picture;
}
