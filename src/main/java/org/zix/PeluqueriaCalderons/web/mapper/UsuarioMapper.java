package org.zix.PeluqueriaCalderons.web.mapper;


import org.zix.PeluqueriaCalderons.dominio.dto.UsuarioDto;
import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "password", ignore = true) // Por seguridad, no mapear password en toDto
    UsuarioDto toDto(UsuarioEntity entity);



    UsuarioEntity toEntity(UsuarioDto dto);

    List<UsuarioDto> toDtoList(List<UsuarioEntity> entities);

    List<UsuarioEntity> toEntityList(List<UsuarioDto> dtos);
}