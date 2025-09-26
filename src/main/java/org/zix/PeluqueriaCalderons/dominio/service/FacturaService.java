package org.zix.PeluqueriaCalderons.dominio.service;

import org.springframework.stereotype.Service;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.dominio.repository.CitaRepository;
import org.zix.PeluqueriaCalderons.dominio.repository.FacturaRepository;

import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaDto> obtenerTodo() {
        return this.facturaRepository.obtenerTodo();
    }

    public FacturaDto buscarFacturaPorCodigo(Long codigoFactura) {
        return this.facturaRepository.buscarFacturaPorCodigo(codigoFactura);
    }

    public FacturaDto guardarFactura(ModFacturaDto modFacturaDto) {
        return this.facturaRepository.guardarFactura(modFacturaDto);
    }

    public FacturaDto modificarFactura(Long codigoFactura,  ModFacturaDto modFacturaDto) {
        return this.facturaRepository.modificarFactura(codigoFactura, modFacturaDto);
    }

    public void eliminarFactura(Long codigoFactura) {
        this.facturaRepository.eliminarFactura(codigoFactura);
    }
}
