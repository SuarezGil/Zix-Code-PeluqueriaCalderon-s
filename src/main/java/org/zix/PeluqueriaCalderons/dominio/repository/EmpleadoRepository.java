package org.zix.PeluqueriaCalderons.dominio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.persistence.entity.EmpleadoEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    Optional<EmpleadoEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    List<EmpleadoEntity> findByActivo(Boolean activo);
    List<EmpleadoEntity> findByPuesto(String puesto);
}