package com.itsupport.repository;

import com.itsupport.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentBreakdownRepository extends JpaRepository<EquipmentBreakdown, EquipmentBreakdownKey> {
    List<EquipmentBreakdown> findAllByEquipmentId(Long id);
}
