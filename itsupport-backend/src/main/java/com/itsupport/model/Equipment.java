package com.itsupport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.List;

/**
 * Represents an Equipment entity in the system.
 *
 * The Equipment class is mapped to the "equipment" table in the database.
 * It represents an individual piece of equipment that can be associated with a client
 * and may experience various breakdowns. This class includes information about the equipment's
 * name, status, associated client, and related breakdowns.
 *
 * Properties:
 *
 * - {@link #id} - Unique identifier for the equipment.
 * - {@link #name} - Name of the equipment.
 * - {@link #status} - Current status of the equipment (e.g., AVAILABLE, IN_SERVICE).
 * - {@link #client} - {@link Client} entity associated with this equipment.
 * - {@link #equipmentBreakdowns} - List of {@link EquipmentBreakdown} entities related to the equipment.
 *
 * Constructors:
 *
 * - {@link @Equipment(Long, String, EquipmentStatus, Client, List)}: Creates an Equipment instance with
 *   the specified parameters.
 * - {@link #Equipment()}: Default constructor for Equipment.
 *
 * The `client` and `equipmentBreakdowns` fields are marked with {@link JsonIgnore} to prevent
 * them from being serialized into JSON, avoiding circular references.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.enums.EquipmentStatus
 * @see com.itsupport.model.Client
 * @see com.itsupport.model.EquipmentBreakdown
 */
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

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EquipmentStatus status;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "category")
    private String category;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "purchase_date")
    @CreationTimestamp
    private Date purchaseDate;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<EquipmentBreakdown> equipmentBreakdowns;
}
