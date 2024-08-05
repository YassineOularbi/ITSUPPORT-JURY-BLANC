package com.itsupport.repository;

import com.itsupport.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
}
