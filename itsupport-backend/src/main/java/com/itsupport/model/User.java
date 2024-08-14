package com.itsupport.model;

import com.itsupport.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
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
 * - {@link #phone} - Phone number of the user.
 * - {@link #address} - Address of the user.
 * - {@link #joinedDate} - Date when the user joined.
 * - {@link #avatarUrl} - URL for the user's avatar.
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "joined_date")
    @CreationTimestamp
    private Date joinedDate;

    @Column(name = "avatar_url", nullable = true)
    private String avatarUrl;

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
