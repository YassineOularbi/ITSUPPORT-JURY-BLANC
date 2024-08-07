package com.itsupport.mapper;

import com.itsupport.dto.EquipmentDto;
import com.itsupport.model.Equipment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    Equipment toEntity(EquipmentDto equipmentDto);
    Equipment partialUpdate(EquipmentDto equipmentDto, @MappingTarget Equipment equipment);
}
