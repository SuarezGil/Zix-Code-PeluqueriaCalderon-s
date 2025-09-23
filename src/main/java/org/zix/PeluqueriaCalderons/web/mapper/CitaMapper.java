package org.zix.PeluqueriaCalderons.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    CitaDto toDto(CitaEntity entity);

    List<CitaDto> toDtos(Iterable<CitaEntity> entities);

    CitaEntity toEntity(CitaDto citaDto);

}
