package com.itsupport.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipment_breakdown")
public class EquipmentBreakdown {

    @EmbeddedId
    private EquipmentBreakdownKey id;

    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @MapsId("breakdownId")
    @JoinColumn(name = "breakdown_id")
    private Breakdown breakdown;
}
