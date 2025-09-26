package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;

import java.time.LocalDate;

public interface FacturaCrud extends CrudRepository<FacturaEntity, Long> {
    FacturaEntity findByClienteCodigoClienteAndFecha(Long codigoCliente, LocalDate fecha);
}
