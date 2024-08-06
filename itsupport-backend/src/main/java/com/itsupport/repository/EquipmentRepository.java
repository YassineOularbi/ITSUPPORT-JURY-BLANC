package com.itsupport.repository;

import com.itsupport.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> getEquipmentByStatus_OutOfService();
}
