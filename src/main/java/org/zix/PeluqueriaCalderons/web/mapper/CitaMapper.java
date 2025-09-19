package org.zix.PeluqueriaCalderons.web.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "cliente.codigoCliente", target = "clienteId")
    @Mapping(source = "servicio.codigoServicio", target = "servicioId") // CORREGIDO
    @Mapping(source = "fechaHora", target = "dateTime")
    CitaDto toDto(CitaEntity entity);

    List<CitaDto> toDto(Iterable<CitaEntity> entities);

    @InheritInverseConfiguration
    CitaEntity toEntity(ModCitaDto dto);

    @Mapping(source = "clienteId", target = "cliente.codigoCliente")
    @Mapping(source = "servicioId", target = "servicio.codigoServicio") // CORREGIDO
    @Mapping(source = "dateTime", target = "fechaHora")
    void modificarEntityFromDto(ModCitaDto modCitaDto, @MappingTarget CitaEntity entity);
}
