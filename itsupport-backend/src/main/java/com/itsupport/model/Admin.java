package com.itsupport.model;

import com.itsupport.enums.Role;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents an Admin entity in the system.
 *
 * The Admin class extends the User class and is mapped to the "admin" table in the database.
 * It represents an administrator user with elevated privileges. By default, the role is set to ADMIN.
 *
 * Constructors:
 *
 * - {@link #Admin(Long, String, String, String, String)}: Creates an Admin instance with the specified parameters.
 * - {@link #Admin()}: Creates an Admin instance with the role set to ADMIN by default.
 *
 * Inherited properties:
 *
 * - {@link User@id} - Unique identifier for the user.
 * - {@link User@fullName} - Full name of the user.
 * - {@link User@mail} - Email address of the user.
 * - {@link User@username} - Username of the user.
 * - {@link User@password} - Password for the user account.
 * - {@link User@role} - Role of the user, which is set to ADMIN by default.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.User
 * @see com.itsupport.enums.Role
 */
@Entity
@Table(name = "admin")
public class Admin extends User {

    /**
     * Creates an Admin instance with the specified parameters.
     *
     * @param id the unique identifier for the admin
     * @param fullName the full name of the admin
     * @param mail the email address of the admin
     * @param username the username of the admin
     * @param password the password of the admin
     */
    public Admin(Long id, String fullName, String mail, String username, String password, Role role, String phone, String address, Date joinedDate, String avatarUrl) {
        super(id, fullName, mail, username, password, Role.ADMIN, phone, address, joinedDate, avatarUrl);
        this.setRole(Role.ADMIN);
    }

    /**
     * Creates an Admin instance with the role set to ADMIN by default.
     */
    public Admin() {
        this.setRole(Role.ADMIN);
    }
}
