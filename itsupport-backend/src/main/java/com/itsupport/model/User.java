package com.itsupport.model;

import com.itsupport.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Represents a user in the system.
 *
 * The User class is a base entity for different types of users (e.g., admins, clients, technicians)
 * in the system. It implements the {@link UserDetails} interface to provide user-related data
 * for Spring Security authentication and authorization.
 *
 * Properties:
 *
 * - {@link #id} - Unique identifier for the user.
 * - {@link #fullName} - Full name of the user.
 * - {@link #mail} - Email address of the user.
 * - {@link #username} - Username for user login.
 * - {@link #password} - Password for user authentication.
 * - {@link #role} - Role of the user, defining their access level.
 *
 * Methods:
 *
 * - {@link #getAuthorities()} - Returns the authorities granted to the user based on their role.
 * - {@link #isAccountNonExpired()} - Indicates whether the account is non-expired.
 * - {@link #isAccountNonLocked()} - Indicates whether the account is non-locked.
 * - {@link #isCredentialsNonExpired()} - Indicates whether the credentials are non-expired.
 * - {@link #isEnabled()} - Indicates whether the user is enabled.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.enums.Role
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements UserDetails {
    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Full name of the user.
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * Email address of the user.
     */
    @Column(name = "mail", nullable = false)
    private String mail;

    /**
     * Username for user login.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Password for user authentication.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Role of the user, defining their access level.
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
