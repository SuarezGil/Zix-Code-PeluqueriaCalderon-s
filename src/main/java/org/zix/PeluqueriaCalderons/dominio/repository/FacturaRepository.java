package org.zix.PeluqueriaCalderons.dominio.repository;

import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;

import java.util.List;

public interface FacturaRepository {
    List<FacturaDto> obtenerTodo();
    FacturaDto buscarFacturaPorCodigo(Long codigoFactura);
    FacturaDto guardarFactura(ModFacturaDto modFacturaDto);
    FacturaDto modificarFactura(Long codigoFactura, ModFacturaDto modFacturaDto);
    void eliminarFactura(Long codigoFactura);
}
