package org.zix.PeluqueriaCalderons.web.mapper;

import org.zix.PeluqueriaCalderons.dominio.dto.InventarioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.CreateInventarioDto;
import org.zix.PeluqueriaCalderons.persistence.entity.InventarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InventarioMapper {

    @Mapping(target = "valorTotal", ignore = true)
    @Mapping(target = "bajoStockMinimo", ignore = true)
    InventarioDto toDto(InventarioEntity entity);

    InventarioEntity toEntity(InventarioDto dto);

    @Mapping(target = "id", ignore = true)
    InventarioEntity fromCreateDto(CreateInventarioDto dto);

    List<InventarioDto> toDtoList(List<InventarioEntity> entities);
}
