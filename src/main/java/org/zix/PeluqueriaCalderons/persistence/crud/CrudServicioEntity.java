package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.ServicioEntity;

public interface CrudServicioEntity extends CrudRepository<ServicioEntity, Long> {
    ServicioEntity findFirstByNombre(String nombre);
}
