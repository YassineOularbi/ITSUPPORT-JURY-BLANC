package com.itsupport.model;

import com.itsupport.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "technician")
public class Technician extends User{
    private Boolean availability;
    public Technician(Long id, String fullName, String mail, String username, String password, Boolean availability) {
        super(id, fullName, mail, username, password, Role.TECHNICIAN);
        this.availability = availability;
    }

    public Technician() {
        this.setRole(Role.TECHNICIAN);
    }
}
