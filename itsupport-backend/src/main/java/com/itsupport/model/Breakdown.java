package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a Breakdown entity in the system.
 *
 * The Breakdown class is mapped to the "breakdown" table in the database. It represents a breakdown
 * or issue that can be associated with equipment. This class contains information about the breakdown
 * and its relationship with equipment instances.
 *
 * Properties:
 *
 * - {@link #id} - Unique identifier for the breakdown.
 * - {@link #name} - Name or description of the breakdown.
 * - {@link #equipmentBreakdowns} - List of {@link EquipmentBreakdown} entities associated with this breakdown.
 *
 * Constructors:
 *
 * - {@link #Breakdown(Long, String, List)}: Creates a Breakdown instance with the specified parameters.
 * - {@link #Breakdown()}: Default constructor for Breakdown.
 *
 * The `equipmentBreakdowns` field is marked with {@link JsonIgnore} to prevent it from being serialized into JSON.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.EquipmentBreakdown
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "breakdown")
public class Breakdown {

    /**
     * Unique identifier for the breakdown.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Name or description of the breakdown.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * List of {@link EquipmentBreakdown} entities associated with this breakdown.
     * This field is ignored during JSON serialization to avoid circular references.
     */
    @OneToMany(mappedBy = "breakdown")
    @JsonIgnore
    private List<EquipmentBreakdown> equipmentBreakdowns;
}
