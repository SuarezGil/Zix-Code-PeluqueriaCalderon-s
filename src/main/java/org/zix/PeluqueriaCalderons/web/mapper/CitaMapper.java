package org.zix.PeluqueriaCalderons.web.mapper;

import org.mapstruct.Mapping;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;

import java.util.List;

public interface CitaMapper {
    @Mapping(source = "fecha_hora", target = "releaseDate")
    public CitaDto toDto(CitaEntity entity);
    public List<CitaDto> toDtos(Iterable<CitaEntity> entities);
}
