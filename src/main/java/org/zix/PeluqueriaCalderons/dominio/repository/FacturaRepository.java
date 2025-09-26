package org.zix.PeluqueriaCalderons.dominio.repository;

import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;

import java.util.List;

public interface FacturaRepository {
    List<FacturaDto> obtenerTodo();
    FacturaDto buscarPorId(Long codigo);
    FacturaDto guardarFactura(FacturaDto facturaDto);
    void eliminarFactura(Long codigo);
    FacturaDto modificarFactura(Long codigo, ModFacturaDto facturaDto);
}