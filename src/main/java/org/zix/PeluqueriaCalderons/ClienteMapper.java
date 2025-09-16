package org.zix.PeluqueriaCalderons;

import org.zix.PeluqueriaCalderons.ClienteEntity;
import org.zix.PeluqueriaCalderons.ClienteDto;
import ch.qos.logback.core.net.server.Client;

import java.util.List;

public interface ClienteMapper {
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "email", target = "correo")
    @Mapping(source = "fechaRegistro", target = "releaseDate")
    public ClienteDto toDto(ClienteEntity entity);
    public List<ClienteDto> toDtos(Iterable<ClienteEntity> entities);

}
