package com.itsupport.model;

import com.itsupport.enums.Role;
import jakarta.persistence.Entity;

@Entity
public class Client extends User{
    public Client(Long id, String fullName, String mail, String username, String password) {
        super(id, fullName, mail, username, password, Role.CLIENT);
    }

    public Client() {
        this.setRole(Role.CLIENT);
    }
}
