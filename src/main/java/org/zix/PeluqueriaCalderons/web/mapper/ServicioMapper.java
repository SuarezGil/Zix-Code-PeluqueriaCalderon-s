package org.zix.PeluqueriaCalderons.web.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ServicioEntity;

import java.util.List;

@Mapper (componentModel = "spring")
public interface ServicioMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "duracion", target = "duration")
    ServicioDto toDto(ServicioEntity entity);

    public List<ServicioDto> toDto(Iterable<ServicioEntity> entities);

    @InheritInverseConfiguration
    public ServicioEntity toEntity(ServicioDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "price", target = "precio")
    @Mapping(source = "duration", target = "duracion")
    public void modificarEntityFromDto(ModServicioDto modServicioDto, @MappingTarget ServicioEntity entity);

}
