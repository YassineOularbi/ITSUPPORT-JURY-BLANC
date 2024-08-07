package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EquipmentStatus status;

    @ManyToOne
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<EquipmentBreakdown> equipmentBreakdowns;
}
