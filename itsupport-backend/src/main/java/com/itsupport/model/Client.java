package com.itsupport.model;

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

    public Client(Long id, String fullName, String mail, String username, String password, List<Equipment> equipments) {
        super(id, fullName, mail, username, password, Role.CLIENT);
        this.equipments = equipments;
    }

    public Client() {
        this.setRole(Role.CLIENT);
    }
}
