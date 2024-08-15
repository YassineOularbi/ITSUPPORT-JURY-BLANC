package com.itsupport.dto;

import com.itsupport.enums.BreakdownPriority;
import com.itsupport.enums.BreakdownType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * Data Transfer Object (DTO) for representing a breakdown.
 *
 * This class is used to encapsulate the data associated with a breakdown in the system. It is used
 * to transfer breakdown-related information between different layers of the application.
 *
 * <p>Example usage:</p>
 * <pre>
 * BreakdownDto breakdownDto = new BreakdownDto();
 * breakdownDto.setName("Engine Failure");
 * </pre>
 *
 * @see com.itsupport.service.BreakdownService
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreakdownDto {
    private String name;
    private String description;
    private BreakdownPriority priority;
    private BreakdownType type;
}
