package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Represents a technician in the system.
 *
 * The Technician class extends the User class and adds additional properties specific to
 * technicians. This class includes a flag for availability and a list of tickets assigned
 * to the technician. It manages the role of the technician and their association with
 * tickets in the system.
 *
 * Properties:
 *
 * - {@link #availability} - Indicates whether the technician is available for new tasks.
 * - {@link #tickets} - List of tickets assigned to this technician.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.User
 * @see com.itsupport.model.Ticket
 */
@Setter
@Getter
@Entity
@Table(name = "technician")
public class Technician extends User {

    /**
     * Indicates whether the technician is available for new tasks.
     */
    private Boolean availability;

    /**
     * List of tickets assigned to this technician.
     */
    @OneToMany(mappedBy = "technician")
    @JsonIgnore
    private List<Ticket> tickets;

    /**
     * Constructs a Technician instance with specified parameters.
     *
     * @param id            The ID of the technician.
     * @param fullName      The full name of the technician.
     * @param mail          The email of the technician.
     * @param username      The username of the technician.
     * @param password      The password of the technician.
     * @param role          The role of the technician (should be TECHNICIAN).
     * @param availability  The availability status of the technician.
     * @param tickets       The list of tickets assigned to the technician.
     */
    public Technician(Long id, String fullName, String mail, String username, String password, Role role, String phone, String address, Date joinedDate, String avatarUrl, Boolean availability, List<Ticket> tickets) {
        super(id, fullName, mail, username, password, Role.TECHNICIAN, phone, address, joinedDate, avatarUrl);
        this.availability = availability;
        this.tickets = tickets;
        this.setRole(Role.TECHNICIAN);
    }

    /**
     * Default constructor for Technician.
     * Sets the role of the technician to TECHNICIAN.
     */
    public Technician() {
        this.setRole(Role.TECHNICIAN);
    }
}
