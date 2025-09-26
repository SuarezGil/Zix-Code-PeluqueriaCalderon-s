package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CrudFacturaEntity extends CrudRepository<FacturaEntity, Long> {
    Optional<FacturaEntity> findFirstByFechaHoraAndCliente_CodigoCliente(LocalDateTime fechaHora, Long codigoCliente);
}
