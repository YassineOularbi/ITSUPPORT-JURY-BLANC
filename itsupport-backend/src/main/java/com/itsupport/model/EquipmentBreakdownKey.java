package com.itsupport.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the composite key for the EquipmentBreakdown entity.
 *
 * The EquipmentBreakdownKey class is used as a composite key to uniquely identify
 * each EquipmentBreakdown relationship. This class is marked as {@link Embeddable},
 * meaning it can be embedded in other entities as part of a composite key.
 *
 * Properties:
 *
 * - {@link #equipmentId} - ID of the associated equipment.
 * - {@link #breakdownId} - ID of the associated breakdown.
 *
 * Methods:
 *
 * - {@link #equals(Object)}: Compares this composite key with another object for equality.
 * - {@link #hashCode()}: Returns a hash code value for the composite key.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.model.Breakdown
 */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentBreakdownKey implements Serializable {

    /**
     * ID of the associated equipment.
     */
    @Column(name = "equipment_id")
    private Long equipmentId;

    /**
     * ID of the associated breakdown.
     */
    @Column(name = "breakdown_id")
    private Long breakdownId;

    /**
     * Compares this composite key with another object for equality.
     *
     * @param o the object to be compared with
     * @return true if the objects are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentBreakdownKey that = (EquipmentBreakdownKey) o;
        return Objects.equals(equipmentId, that.equipmentId) && Objects.equals(breakdownId, that.breakdownId);
    }

    /**
     * Returns a hash code value for the composite key.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(equipmentId, breakdownId);
    }
}
