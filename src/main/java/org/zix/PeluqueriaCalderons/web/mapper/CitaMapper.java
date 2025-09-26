package org.zix.PeluqueriaCalderons.web.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "cliente.codigoCliente", target = "clienteId")
    @Mapping(source = "servicio.codigoServicio", target = "servicioId")
    @Mapping(source = "fechaHora", target = "dateTime")
    CitaDto toDto(CitaEntity entity);

    List<CitaDto> toDto(List<CitaEntity> entities);

    @Mapping(source = "clienteId", target = "cliente.codigoCliente")
    @Mapping(source = "servicioId", target = "servicio.codigoServicio")
    @Mapping(source = "dateTime", target = "fechaHora")
    CitaEntity toEntity(ModCitaDto dto);

    @Mapping(source = "clienteId", target = "cliente.codigoCliente")
    @Mapping(source = "servicioId", target = "servicio.codigoServicio")
    @Mapping(source = "dateTime", target = "fechaHora")
    void modificarEntityFromDto(ModCitaDto modCitaDto, @MappingTarget CitaEntity entity);

    // Método para convertir LocalDateTime a Date
    default Date map(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return java.util.Date.from(value.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

    // Método para convertir Date a LocalDateTime
    default LocalDateTime map(Date value) {
        if (value == null) {
            return null;
        }
        return value.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    }
}