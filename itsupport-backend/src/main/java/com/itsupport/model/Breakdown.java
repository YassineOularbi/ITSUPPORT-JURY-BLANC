package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.enums.BreakdownPriority;
import com.itsupport.enums.BreakdownType;
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
 * - {@link #description} - Description of the breakdown.
 * - {@link #priority} - Priority of the breakdown.
 * - {@link #type} - Type of the breakdown.
 * - {@link #equipmentBreakdowns} - List of {@link EquipmentBreakdown} entities associated with this breakdown.
 *
 * Constructors:
 *
 * - {@link #Breakdown(Long, String, String, BreakdownPriority, BreakdownType, List)}: Creates a Breakdown instance with the specified parameters.
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private BreakdownPriority priority;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BreakdownType type;

    @OneToMany(mappedBy = "breakdown")
    @JsonIgnore
    private List<EquipmentBreakdown> equipmentBreakdowns;
}
