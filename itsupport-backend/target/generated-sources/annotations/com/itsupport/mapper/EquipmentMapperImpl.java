package com.itsupport.mapper;

import com.itsupport.dto.EquipmentDto;
import com.itsupport.model.Equipment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-15T01:35:41+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class EquipmentMapperImpl implements EquipmentMapper {

    @Override
    public Equipment toEntity(EquipmentDto equipmentDto) {
        if ( equipmentDto == null ) {
            return null;
        }

        Equipment equipment = new Equipment();

        equipment.setName( equipmentDto.getName() );
        equipment.setModel( equipmentDto.getModel() );
        equipment.setSerialNumber( equipmentDto.getSerialNumber() );
        equipment.setCategory( equipmentDto.getCategory() );
        equipment.setPurchasePrice( equipmentDto.getPurchasePrice() );

        return equipment;
    }

    @Override
    public Equipment partialUpdate(EquipmentDto equipmentDto, Equipment equipment) {
        if ( equipmentDto == null ) {
            return equipment;
        }

        equipment.setName( equipmentDto.getName() );
        equipment.setModel( equipmentDto.getModel() );
        equipment.setSerialNumber( equipmentDto.getSerialNumber() );
        equipment.setCategory( equipmentDto.getCategory() );
        equipment.setPurchasePrice( equipmentDto.getPurchasePrice() );

        return equipment;
    }
}
