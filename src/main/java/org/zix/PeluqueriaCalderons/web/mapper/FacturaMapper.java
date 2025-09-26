package org.zix.PeluqueriaCalderons.web.mapper;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {
    @Mapping(source = "fechaHora", target = "date")
    @Mapping(source = "total", target = "totalidad")
    @Mapping(source = "cliente.codigoCliente", target = "clienteId" )
    @Mapping(source = "empleado.id", target = "empleadoId")
    FacturaDto toDto( FacturaEntity factura );


    List<FacturaDto> toDto(Iterable<FacturaEntity> facturas);

    @InheritInverseConfiguration
    FacturaEntity toEntity(ModFacturaDto dto);

    @Mapping(source = "date", target = "fechaHora")
    @Mapping(source = "totalidad", target = "total")
    @Mapping(source = "clienteId", target = "cliente.codigoCliente" )
    @Mapping(source = "empleadoId", target = "empleado.id")
     void modificarEntityFromDto(ModFacturaDto modFacturaDto,@MappingTarget FacturaEntity factura );
}
