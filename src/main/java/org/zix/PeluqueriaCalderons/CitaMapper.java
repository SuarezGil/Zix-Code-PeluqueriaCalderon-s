package org.zix.PeluqueriaCalderons;

import java.util.List;

public interface CitaMapper {
    @Mapping(source = "fecha_hora", target = "releaseDate")
    public CitaDto toDto(CitaEntity entity);
    public List<CitaDto> toDtos(Iterable<CitaEntity> entities);
}
