package org.zix.PeluqueriaCalderons.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    @Mapping(source = "total", target = "total")
    @Mapping(source = "fecha", target = "fecha")
    public FacturaDto toDto(FacturaEntity entity);
    public List<FacturaDto> toDtos(Iterable<FacturaEntity> entities);

    FacturaEntity toEntity(FacturaDto facturaDto);
}
