package com.itsupport.enums;

/**
 * Represents the different roles in the system.
 *
 * This enum defines the roles assigned to users, determining their access and permissions within the system.
 *
 * Roles:
 * - ADMIN: A user with administrative privileges, capable of managing other users and system settings.
 * - TECHNICIAN: A user responsible for handling equipment issues and maintenance tasks.
 * - CLIENT: A user who utilizes the system to report issues and manage equipment.
 *
 * Example usage:
 *
 * Role userRole = Role.CLIENT;
 * if (userRole == Role.ADMIN) {
 *     // Grant access to admin functionalities
 * }
 *
 * @see com.itsupport.model.User
 * @see com.itsupport.service.AdminService
 * @see com.itsupport.service.ClientService
 * @see com.itsupport.service.TechnicianService
 */
public enum Role {
    ADMIN,
    TECHNICIAN,
    CLIENT
}
