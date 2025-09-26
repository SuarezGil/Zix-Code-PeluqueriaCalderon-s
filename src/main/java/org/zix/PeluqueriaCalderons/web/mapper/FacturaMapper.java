package org.zix.PeluqueriaCalderons.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.EmpleadoEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    @Mapping(source = "cliente.codigoCliente", target = "cliente_id")
    @Mapping(source = "empleado.id", target = "empleado_id")
    FacturaDto toDto(FacturaEntity entity);

    @Mapping(source = "cliente_id", target = "cliente")
    @Mapping(source = "empleado_id", target = "empleado")
    FacturaEntity toEntity(FacturaDto dto);

    List<FacturaDto> toDto(Iterable<FacturaEntity> facturas);

    default ClienteEntity mapCliente(Long id) {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(id);
        return cliente;
    }

    default EmpleadoEntity mapEmpleado(Long id) {
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setId(id);
        return empleado;
    }
}
