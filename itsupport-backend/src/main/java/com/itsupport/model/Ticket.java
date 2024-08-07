package com.itsupport.model;

import com.itsupport.enums.TicketStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "reporting_date", nullable = false)
    @CreationTimestamp
    private Date reportingDate;
    @Column(name = "last_updated", nullable = false)
    @UpdateTimestamp
    private Date lastUpdated;
    @Column(name = "resolution_date")
    private Date resolutionDate;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Technician technician;
    @ManyToOne
    private EquipmentBreakdown equipmentBreakdown;
}
