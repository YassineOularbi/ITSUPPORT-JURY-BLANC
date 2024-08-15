package com.itsupport.dto;

import com.itsupport.enums.EquipmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

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
    private String name;
    private String model;
    private String serialNumber;
    private String category;
    private Double purchasePrice;
    private MultipartFile picture;
}
