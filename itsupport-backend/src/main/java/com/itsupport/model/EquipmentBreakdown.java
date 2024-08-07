package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents the association between Equipment and Breakdown.
 *
 * The EquipmentBreakdown class serves as an entity to model the relationship between
 * equipment and breakdowns. It uses a composite key defined by {@link EquipmentBreakdownKey}
 * to uniquely identify each relationship instance. This class also maintains references to
 * associated {@link Equipment}, {@link Breakdown}, and {@link Ticket} entities.
 *
 * Properties:
 *
 * - {@link #id} - Composite key that uniquely identifies this EquipmentBreakdown instance.
 * - {@link #equipment} - The equipment associated with this breakdown.
 * - {@link #breakdown} - The breakdown associated with this equipment.
 * - {@link #tickets} - List of tickets related to this EquipmentBreakdown.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.EquipmentBreakdownKey
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.model.Breakdown
 * @see com.itsupport.model.Ticket
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipment_breakdown")
public class EquipmentBreakdown {

    /**
     * Composite key that uniquely identifies this EquipmentBreakdown instance.
     */
    @EmbeddedId
    private EquipmentBreakdownKey id;

    /**
     * The equipment associated with this breakdown.
     */
    @ManyToOne
    @MapsId("equipmentId")
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    /**
     * The breakdown associated with this equipment.
     */
    @ManyToOne
    @MapsId("breakdownId")
    @JoinColumn(name = "breakdown_id")
    private Breakdown breakdown;

    /**
     * List of tickets related to this EquipmentBreakdown.
     */
    @OneToMany(mappedBy = "equipmentBreakdown")
    @JsonIgnore
    private List<Ticket> tickets;
}
