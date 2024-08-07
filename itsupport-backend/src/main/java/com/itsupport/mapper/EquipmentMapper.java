package com.itsupport.mapper;

import com.itsupport.dto.EquipmentDto;
import com.itsupport.model.Equipment;
import org.mapstruct.*;

/**
 * Mapper interface for converting between Equipment entities and EquipmentDto.
 *
 * This interface uses MapStruct to automate the mapping between the Equipment entity
 * and the EquipmentDto. It provides methods for converting an EquipmentDto to an Equipment
 * entity and for partially updating an Equipment entity with data from an EquipmentDto.
 *
 * Mapping methods:
 *
 * - {@link #toEntity(EquipmentDto)}: Converts an EquipmentDto object to an Equipment entity.
 * - {@link #partialUpdate(EquipmentDto, Equipment)}: Partially updates an Equipment entity with
 *   data from an EquipmentDto. Only the fields present in the EquipmentDto will be updated.
 *
 * Example usage:
 *
 * {@code
 * Equipment equipment = equipmentMapper.toEntity(equipmentDto);
 * equipmentMapper.partialUpdate(equipmentDto, existingEquipment);
 * }
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 *
 * @see com.itsupport.model.Equipment
 * @see com.itsupport.dto.EquipmentDto
 */
@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    /**
     * Converts an EquipmentDto object to an Equipment entity.
     *
     * @param equipmentDto the EquipmentDto object to convert
     * @return the corresponding Equipment entity
     */
    Equipment toEntity(EquipmentDto equipmentDto);

    /**
     * Partially updates an Equipment entity with data from an EquipmentDto.
     *
     * @param equipmentDto the EquipmentDto containing update data
     * @param equipment the Equipment entity to update
     * @return the updated Equipment entity
     */
    Equipment partialUpdate(EquipmentDto equipmentDto, @MappingTarget Equipment equipment);
}
