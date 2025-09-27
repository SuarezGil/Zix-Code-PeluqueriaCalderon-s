package org.zix.PeluqueriaCalderons.dominio.repository;

import org.zix.PeluqueriaCalderons.persistence.entity.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, Long> {

    Optional<InventarioEntity> findByNombreProducto(String nombreProducto);
    List<InventarioEntity> findByCategoria(String categoria);
    List<InventarioEntity> findByCantidadLessThanEqual(Integer cantidad);
    List<InventarioEntity> findByPrecioUnitarioBetween(BigDecimal minPrecio, BigDecimal maxPrecio);
    boolean existsByNombreProducto(String nombreProducto);
    boolean existsByNombreProductoAndIdNot(String nombreProducto, Long id);

    @Query("SELECT i FROM InventarioEntity i WHERE i.cantidad <= i.minimoStock AND i.minimoStock > 0")
    List<InventarioEntity> findProductosBajoStockMinimo();

    @Query("SELECT i FROM InventarioEntity i WHERE i.nombreProducto LIKE %:nombre%")
    List<InventarioEntity> findByNombreProductoContaining(@Param("nombre") String nombre);

    @Query("SELECT SUM(i.precioUnitario * i.cantidad) FROM InventarioEntity i")
    BigDecimal getValorTotalInventario();

    @Query("SELECT COUNT(i) FROM InventarioEntity i WHERE i.cantidad = 0")
    Long countProductosAgotados();

    @Query("SELECT i.categoria, SUM(i.cantidad) FROM InventarioEntity i GROUP BY i.categoria")
    List<Object[]> getStockPorCategoria();
}
