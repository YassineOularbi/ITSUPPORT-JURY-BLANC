package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "technician")
public class Technician extends User{

    private Boolean availability;

    @OneToMany(mappedBy = "technician")
    @JsonIgnore
    private List<Ticket> tickets;

    public Technician(Long id, String fullName, String mail, String username, String password, Role role, Boolean availability, List<Ticket> tickets) {
        super(id, fullName, mail, username, password, role);
        this.availability = availability;
        this.tickets = tickets;
        this.setRole(Role.TECHNICIAN);
    }

    public Technician() {
        this.setRole(Role.TECHNICIAN);
    }
}
