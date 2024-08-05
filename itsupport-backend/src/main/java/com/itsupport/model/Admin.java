package com.itsupport.model;

import com.itsupport.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User{
    public Admin(Long id, String fullName, String mail, String username, String password) {
        super(id, fullName, mail, username, password, Role.ADMIN);
    }

    public Admin() {
        this.setRole(Role.ADMIN);
    }
}
