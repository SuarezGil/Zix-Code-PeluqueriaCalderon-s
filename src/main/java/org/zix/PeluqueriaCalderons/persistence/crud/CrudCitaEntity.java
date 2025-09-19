package org.zix.PeluqueriaCalderons.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.zix.PeluqueriaCalderons.persistence.entity.CitaEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface CrudCitaEntity extends CrudRepository<CitaEntity, Long> {
    Optional<CitaEntity> findFirstByFechaHora(LocalDateTime fechaHora);
}
