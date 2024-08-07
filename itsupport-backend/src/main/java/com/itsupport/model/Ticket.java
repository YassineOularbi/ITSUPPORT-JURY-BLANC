package com.itsupport.model;

import com.itsupport.enums.TicketStatus;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.sql.Date;

/**
 * Represents a support ticket in the system.
 *
 * The Ticket class encapsulates information about support requests or issues reported
 * by clients. It includes details about the ticket's description, reporting and resolution
 * dates, current status, and associations with clients, technicians, and equipment breakdowns.
 *
 * Properties:
 *
 * - {@link #id} - Unique identifier for the ticket.
 * - {@link #description} - Description of the issue or request.
 * - {@link #reportingDate} - Date when the ticket was reported.
 * - {@link #lastUpdated} - Date when the ticket was last updated.
 * - {@link #resolutionDate} - Date when the ticket was resolved, if applicable.
 * - {@link #status} - Current status of the ticket (e.g., PENDING, PROCESSING).
 * - {@link #client} - The client who reported the ticket.
 * - {@link #technician} - The technician assigned to handle the ticket.
 * - {@link #equipmentBreakdown} - The equipment breakdown associated with the ticket.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Client
 * @see com.itsupport.model.Technician
 * @see com.itsupport.model.EquipmentBreakdown
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "ticket")
public class Ticket {

    /**
     * Unique identifier for the ticket.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Description of the issue or request.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Date when the ticket was reported.
     */
    @Column(name = "reporting_date", nullable = false)
    @CreationTimestamp
    private Date reportingDate;

    /**
     * Date when the ticket was last updated.
     */
    @Column(name = "last_updated", nullable = false)
    @UpdateTimestamp
    private Date lastUpdated;

    /**
     * Date when the ticket was resolved, if applicable.
     */
    @Column(name = "resolution_date")
    private Date resolutionDate;

    /**
     * Current status of the ticket (e.g., PENDING, PROCESSING).
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    /**
     * The client who reported the ticket.
     */
    @ManyToOne
    private Client client;

    /**
     * The technician assigned to handle the ticket.
     */
    @ManyToOne
    private Technician technician;

    /**
     * The equipment breakdown associated with the ticket.
     */
    @ManyToOne
    private EquipmentBreakdown equipmentBreakdown;
}
