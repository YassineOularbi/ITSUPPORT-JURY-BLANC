package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client extends User{

    @OneToMany(mappedBy = "client")
    private List<Equipment> equipments;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Ticket> tickets;

    public Client(Long id, String fullName, String mail, String username, String password, Role role, List<Equipment> equipments, List<Ticket> tickets) {
        super(id, fullName, mail, username, password, role);
        this.equipments = equipments;
        this.tickets = tickets;
        this.setRole(Role.CLIENT);
    }

    public Client() {
        this.setRole(Role.CLIENT);
    }
}
