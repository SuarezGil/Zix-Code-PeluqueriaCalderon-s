package org.zix.PeluqueriaCalderons.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.InheritInverseConfiguration;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "codigoCliente", target = "codigoCliente")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "telefono", target = "tel")
    @Mapping(source = "correo", target = "email")
    @Mapping(source = "fechaRegistro", target = "registrationDate")
    ClienteDto toDto(ClienteEntity entity);

    List<ClienteDto> toDto(Iterable<ClienteEntity> entities);

    @InheritInverseConfiguration
    ClienteEntity toEntity(ClienteDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "tel", target = "telefono")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "registrationDate", target = "fechaRegistro")
    void modificarEntityFromDto(ModClienteDto modClienteDto, @MappingTarget ClienteEntity entity);

    // Conversiones automáticas
    default LocalDate map(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    default Date map(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
