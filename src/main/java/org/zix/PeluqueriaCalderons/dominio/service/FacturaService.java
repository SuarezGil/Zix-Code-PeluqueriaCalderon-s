package org.zix.PeluqueriaCalderons.dominio.service;

import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.dominio.repository.FacturaRepository;

import java.util.List;

public class FacturaService {
    private final FacturaRepository facuturaRepository;

    public FacturaService(FacturaRepository facuturaRepository) {
        this.facuturaRepository = facuturaRepository;
    }


    public List<FacturaDto> obtenerTodo() {
        return this.facuturaRepository.obtenerTodo();
    }

    public  FacturaDto buscarPorId(Long codigo){
        return this.facuturaRepository.buscarPorId(codigo);
    }

    public FacturaDto guardarFactura(FacturaDto facturaDto){
        return this.facuturaRepository.guardarFactura(facturaDto);

    }
    public FacturaDto modificarFactura(Long codigo, ModFacturaDto facturaDto){
        return this.facuturaRepository.modificarFactura(codigo ,facturaDto);
    }

    public void eliminarFactura(Long codigo){
        this.facuturaRepository.eliminarFactura(codigo);
    }
}
