package com.itsupport.mapper;

import com.itsupport.dto.BreakdownDto;
import com.itsupport.model.Breakdown;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Breakdown entities and BreakdownDto.
 *
 * This interface uses MapStruct to automate the mapping between the Breakdown entity
 * and the BreakdownDto. It also provides a method for partially updating a Breakdown
 * entity with data from a BreakdownDto.
 *
 * Mapping methods:
 *
 * - {@link #toEntity(BreakdownDto)}: Converts a BreakdownDto object to a Breakdown entity.
 * - {@link #partialUpdate(BreakdownDto, Breakdown)}: Partially updates a Breakdown entity with
 *   data from a BreakdownDto. Only the fields present in the BreakdownDto will be updated.
 *
 * Example usage:
 *
 * {@code
 * Breakdown breakdown = breakdownMapper.toEntity(breakdownDto);
 * breakdownMapper.partialUpdate(breakdownDto, existingBreakdown);
 * }
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Breakdown
 * @see com.itsupport.dto.BreakdownDto
 */
@Mapper(componentModel = "spring")
public interface BreakdownMapper {

    /**
     * Converts a BreakdownDto object to a Breakdown entity.
     *
     * @param breakdownDto the BreakdownDto object to convert
     * @return the corresponding Breakdown entity
     */
    Breakdown toEntity(BreakdownDto breakdownDto);

    /**
     * Partially updates a Breakdown entity with data from a BreakdownDto.
     *
     * @param breakdownDto the BreakdownDto containing update data
     * @param breakdown the Breakdown entity to update
     */
    Breakdown partialUpdate(BreakdownDto breakdownDto, @MappingTarget Breakdown breakdown);
}
