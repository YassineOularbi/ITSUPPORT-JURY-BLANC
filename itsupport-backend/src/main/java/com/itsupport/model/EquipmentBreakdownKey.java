package com.itsupport.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentBreakdownKey implements Serializable {

    @Column(name = "equipment_id")
    private Long equipmentId;

    @Column(name = "breakdown_id")
    private Long breakdownId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentBreakdownKey that = (EquipmentBreakdownKey) o;
        return Objects.equals(equipmentId, that.equipmentId) && Objects.equals(breakdownId, that.breakdownId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId, breakdownId);
    }
}
