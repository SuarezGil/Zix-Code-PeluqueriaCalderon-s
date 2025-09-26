package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CrudCitaEntity extends CrudRepository<CitaEntity, Long> {

    // Buscar cita por fecha y hora exacta
    Optional<CitaEntity> findByFechaHora(LocalDateTime fechaHora);

    // Buscar cita por fecha y hora excluyendo un código específico (para updates)
    Optional<CitaEntity> findByFechaHoraAndCodigoNot(LocalDateTime fechaHora, Long codigo);
}