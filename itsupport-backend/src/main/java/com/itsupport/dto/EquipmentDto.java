package com.itsupport.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for representing equipment.
 *
 * This class is used to encapsulate the data associated with an equipment item in the system. It is used
 * to transfer equipment-related information between different layers of the application.
 *
 * <p>Example usage:</p>
 * <pre>
 * EquipmentDto equipmentDto = new EquipmentDto();
 * equipmentDto.setName("Laptop");
 * </pre>
 *
 * @see com.itsupport.service.EquipmentService
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {

    /**
     * Name or description of the equipment.
     * This field provides a brief label or identifier for the equipment.
     *
     * Example: "Laptop", "Projector", "Printer"
     */
    private String name;
}
