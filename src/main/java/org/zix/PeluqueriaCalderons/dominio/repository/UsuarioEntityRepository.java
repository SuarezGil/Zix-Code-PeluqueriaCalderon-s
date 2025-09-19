package org.zix.PeluqueriaCalderons.dominio.repository;


import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
    Optional<UsuarioEntity> findByUsernameAndPassword(String username, String password);
    List<UsuarioEntity> findByActivoTrue();
    List<UsuarioEntity> findByRol(UsuarioEntity.Rol rol);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Long id);
}
