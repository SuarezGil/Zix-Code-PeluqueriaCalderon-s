package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;

public interface CrudClienteEntity extends CrudRepository<ClienteEntity, Long> {
    ClienteEntity findFirstByCorreo(String email);
}
