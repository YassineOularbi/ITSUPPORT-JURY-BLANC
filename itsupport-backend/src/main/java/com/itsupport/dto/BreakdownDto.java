package com.itsupport.dto;

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
 * @see com.itsupport.controller.AdminController
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreakdownDto {

    /**
     * Name or title of the breakdown.
     * This field provides a brief description or label for the breakdown.
     *
     * Example: "Engine Failure", "Power Outage"
     */
    private String name;
}
