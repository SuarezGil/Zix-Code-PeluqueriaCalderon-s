package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FacturaCrud extends CrudRepository<FacturaEntity,Long> {
    CitaEntity findByClienteIdAndFechaHora(Long clienteId, LocalDate fecha);
    List<CitaEntity> findByClienteId(Long clienteId);
}
