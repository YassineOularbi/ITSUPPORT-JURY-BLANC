package com.itsupport.repository;

import com.itsupport.enums.EquipmentStatus;
import com.itsupport.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> getEquipmentByStatus(EquipmentStatus status);

    List<Equipment> getEquipmentByClientId(Long id);
}
