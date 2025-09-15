package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;

public interface CrudClienteEntity extends CrudRepository<ClienteEntity, Long> {


    //    PeliculaEntity [metodo interno]+[atributo]
    PeliculaEntity findFirstByNombre(String nombre);
}
