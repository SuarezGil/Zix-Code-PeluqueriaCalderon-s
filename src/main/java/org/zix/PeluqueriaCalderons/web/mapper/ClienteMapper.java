package org.zix.PeluqueriaCalderons.web.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;

import java.util.Iterator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

        // Firmas de metodos

        // toDto:(convertir a DTO)
        @Mapping(source = "nombre", target = "name")
        @Mapping(source = "telefono", target = "tel")
        @Mapping(source = "correo", target = "email")
        @Mapping(source = "fechaRegistro", target = "registrationDate")
        public ClienteDto toDto(ClienteEntity entity);

        public List<ClienteDto> toDto(Iterable<ClienteEntity> entities);
        //para convertir DTO a entity -> toEntity
        @InheritInverseConfiguration
        ClienteEntity toEntity(ClienteDto dto);


    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "tel", target = "telefono")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "registrationDate", target = "fechaRegistro") // ✅ corregido
        public void modificarEntityFromDto(ModClienteDto modClienteDto, @MappingTarget ClienteEntity entity);

}
