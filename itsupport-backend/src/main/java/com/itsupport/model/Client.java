package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Represents a Client entity in the system.
 *
 * The Client class extends the User class and is mapped to the "client" table in the database.
 * It represents a client who can own equipment and create tickets. This class includes information
 * about the client, their equipment, and their tickets.
 *
 * Properties:
 *
 * - {@link #equipments} - List of {@link Equipment} entities associated with the client.
 * - {@link #tickets} - List of {@link Ticket} entities associated with the client.
 *
 * Constructors:
 *
 * - {@link #Client(Long, String, String, String, String, Role, List, List)}: Creates a Client instance with
 *   the specified parameters.
 * - {@link #Client()}: Default constructor for Client.
 *
 * The `tickets` field is marked with {@link JsonIgnore} to prevent it from being serialized into JSON.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.model.Ticket
 */
@Entity
@Getter
@Setter
@Table(name = "client")
public class Client extends User {

    /**
     * List of {@link Equipment} entities associated with the client.
     */
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Equipment> equipments;

    /**
     * List of {@link Ticket} entities associated with the client.
     * This field is ignored during JSON serialization to avoid circular references.
     */
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Ticket> tickets;

    /**
     * Creates a Client instance with the specified parameters.
     *
     * @param id Unique identifier for the client.
     * @param fullName Full name of the client.
     * @param mail Email address of the client.
     * @param username Username for the client.
     * @param password Password for the client.
     * @param role Role of the client.
     * @param equipments List of equipment owned by the client.
     * @param tickets List of tickets created by the client.
     */
    public Client(Long id, String fullName, String mail, String username, String password, Role role, String phone, String address, Date joinedDate, String avatarUrl, List<Equipment> equipments, List<Ticket> tickets) {
        super(id, fullName, mail, username, password, Role.CLIENT, phone, address, joinedDate, avatarUrl);
        this.equipments = equipments;
        this.tickets = tickets;
        this.setRole(Role.CLIENT);
    }

    /**
     * Default constructor for Client.
     */
    public Client() {
        this.setRole(Role.CLIENT);
    }
}
