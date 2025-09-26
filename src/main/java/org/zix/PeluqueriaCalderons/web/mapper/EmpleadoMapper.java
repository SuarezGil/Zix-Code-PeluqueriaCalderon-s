package org.zix.PeluqueriaCalderons.web.mapper;

import org.zix.PeluqueriaCalderons.persistence.entity.EmpleadoEntity;
import org.zix.PeluqueriaCalderons.dominio.dto.EmpleadoDto;
import org.zix.PeluqueriaCalderons.dominio.dto.CreateEmpleadoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    EmpleadoDto toDto(EmpleadoEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", constant = "true")
    EmpleadoEntity toEntity(EmpleadoDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", constant = "true")
    EmpleadoEntity fromCreateDto(CreateEmpleadoDto dto);
}
