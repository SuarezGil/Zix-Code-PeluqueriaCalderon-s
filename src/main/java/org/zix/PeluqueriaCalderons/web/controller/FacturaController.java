package org.zix.PeluqueriaCalderons.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.dominio.service.FacturaService;

import java.util.List;

@RestController
@RequestMapping("/facturas/v1")
@Tag(name = "Facturas", description = "Operaciones crud sobre las facturas en peluqueria calderons")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDto>> obtenerTodos() {
        return  ResponseEntity.ok(this.facturaService.obtenerTodo());
    }


    @GetMapping ("{codigoFactura}")
    public ResponseEntity<FacturaDto> obtenerFacturaPorCodigo( @Parameter(description = "Identificador de la cita a recuperar", example = "1")
                                                                   @PathVariable Long codigoFactura) {
        return ResponseEntity.ok(this.facturaService.buscarFacturaPorCodigo(codigoFactura));
    }

    @PostMapping
    public ResponseEntity<FacturaDto> crearFactura(@RequestBody @Valid @Parameter(description = "Datos de la factura a registrar") ModFacturaDto modFacturaDto) {
    FacturaDto facturaCreada = this.facturaService.guardarFactura(modFacturaDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(facturaCreada);
    }

    @PutMapping ("{codigoFactura}")
    public ResponseEntity <FacturaDto> modificarFactura(@Parameter(description = "Identificador de la factura a modificar", example = "1")
                                                            @PathVariable Long codigoFactura,
                                                        @Valid @RequestBody
                                                            @Parameter(description = "Nuevos datos para la factura") ModFacturaDto modFacturaDto) {
        FacturaDto facturaActualizada = this.facturaService.modificarFactura(codigoFactura, modFacturaDto);
        return ResponseEntity.ok(facturaActualizada);
    }
    @DeleteMapping("{codigoFactura}")
    @Operation(
            summary = "Eliminar una factura",
            description = "Elimina la factura que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Factura eliminada con éxito"),
                    @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarCita(
            @Parameter(description = "Identificador de la cita a eliminar", example = "1")
            @PathVariable Long codigoFactura) {
        this.facturaService.eliminarFactura(codigoFactura);
        return ResponseEntity.noContent().build();
    }
}
