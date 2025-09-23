package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaCrud extends CrudRepository<CitaEntity,Long> {
    CitaEntity findByClienteIdAndFechaHora(Long clienteId, LocalDateTime fechaHora);
    List<CitaEntity> findByClienteId(Long clienteId);  // Buscar por ID de cliente
    List<CitaEntity> findByFechaHora(LocalDateTime fechaHora);  // Buscar por fecha y hora
}
