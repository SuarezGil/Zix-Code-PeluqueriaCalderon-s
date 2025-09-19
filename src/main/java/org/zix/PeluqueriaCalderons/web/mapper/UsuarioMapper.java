package org.zix.PeluqueriaCalderons.web.mapper;

import org.zix.PeluqueriaCalderons.dominio.dto.LoginResponseDto;
import org.zix.PeluqueriaCalderons.dominio.dto.UsuarioDto;
import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Para operaciones que necesitan el password (usado internamente)
    @Named("conPassword")
    UsuarioDto toDto(UsuarioEntity entity);

    // Para respuestas seguras (sin password) - USAR ESTE PARA LA MAYORÍA DE CASOS
    @Named("sinPassword")
    @Mapping(target = "password", ignore = true)
    UsuarioDto toDtoSeguro(UsuarioEntity entity);

    UsuarioEntity toEntity(UsuarioDto dto);

    // Métodos para listas - especificar cuál método usar
    @Named("listaConPassword")
    List<UsuarioDto> toDtoList(List<UsuarioEntity> entities);

    @Named("listaSinPassword")
    @Mapping(target = "password", ignore = true)
    List<UsuarioDto> toDtoListSeguro(List<UsuarioEntity> entities);

    @Mapping(target = "mensaje", ignore = true)
    LoginResponseDto toLoginResponseDto(UsuarioEntity entity);

    // Método por defecto para las conversiones automáticas
    default UsuarioDto map(UsuarioEntity entity) {
        return toDtoSeguro(entity); // Usar el seguro por defecto
    }
}