package com.itsupport.repository;

import com.itsupport.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    List<Technician> findTechniciansByAvailabilityIsTrue();
}
