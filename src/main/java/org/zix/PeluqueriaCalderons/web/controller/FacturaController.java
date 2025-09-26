package org.zix.PeluqueriaCalderons.web.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.dominio.service.FacturaService;

import java.util.List;

@RestController
@RequestMapping("/facturas/v1")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<FacturaDto> obtenerFacturas() {
        return facturaService.obtenerTodo();
    }

    @GetMapping("/{id}")
    public FacturaDto obtenerFactura(@PathVariable Long id) {
        return facturaService.buscarPorId(id);
    }

    @PostMapping
    public FacturaDto guardarFactura(@RequestBody @Valid FacturaDto facturaDto) {
        return facturaService.guardarFactura(facturaDto);
    }

    @PutMapping("/{id}")
    public FacturaDto modificarFactura(@PathVariable Long id, @RequestBody @Valid ModFacturaDto modFacturaDto) {
        return facturaService.modificarFactura(id, modFacturaDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
    }
}

