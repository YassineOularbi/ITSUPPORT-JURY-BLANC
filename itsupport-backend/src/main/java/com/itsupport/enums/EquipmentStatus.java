package com.itsupport.enums;

/**
 * Enumeration representing the various statuses of equipment.
 *
 * This enum defines the possible statuses that an equipment can have within the system.
 * It is used to track and manage the condition of equipment, facilitating operations such as
 * maintenance, assignment, and reporting.
 *
 * Possible statuses:
 * - AVAILABLE: The equipment is available for use.
 * - IN_SERVICE: The equipment is currently in use.
 * - OUT_OF_SERVICE: The equipment is not currently in use but is not broken down.
 * - BROKEN_DOWN: The equipment is broken and cannot be used until repaired.
 *
 * Example usage:
 *
 * EquipmentStatus status = EquipmentStatus.AVAILABLE;
 * if (status == EquipmentStatus.BROKEN_DOWN) {
 *     // Handle broken down equipment
 * }
 *
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.service.EquipmentService
 */
public enum EquipmentStatus {
    AVAILABLE,
    IN_SERVICE,
    OUT_OF_SERVICE,
    BROKEN_DOWN
}
