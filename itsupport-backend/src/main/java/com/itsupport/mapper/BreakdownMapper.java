package com.itsupport.mapper;

import com.itsupport.dto.BreakdownDto;
import com.itsupport.model.Breakdown;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BreakdownMapper {
    Breakdown toEntity(BreakdownDto breakdownDto);

    Breakdown partialUpdate(BreakdownDto breakdownDto, @MappingTarget Breakdown breakdown);
}
