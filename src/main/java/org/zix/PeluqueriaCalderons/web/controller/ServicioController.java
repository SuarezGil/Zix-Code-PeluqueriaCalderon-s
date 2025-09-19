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
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
import org.zix.PeluqueriaCalderons.dominio.service.ClienteService;
import org.zix.PeluqueriaCalderons.dominio.service.ServicioService;

import java.util.List;

@RestController
@RequestMapping("/servicios/v1")
@Tag(name = "Servicios", description = "Operaciones (CRUD) sobre los servicios de PeluqueriaCalderons")
public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    //4
    @GetMapping
    public ResponseEntity<List<ServicioDto>> obtenerTodo() {
        return ResponseEntity.ok(this.servicioService.obtenerTodo());
    }

    @GetMapping("{codigoServicio}")
    @Operation(summary = "Obtener un servicio a partir de su identificador",
            description = "Retorna al servicio que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Servicio fue encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Servicio no encontrado", content = @Content)
            })
    public ResponseEntity<ServicioDto> obtenerServicioPorCodigo(
            @Parameter(description = "Identificador del cliente a recuperar", example = "1")
            @PathVariable Long codigoServicio) {
        return ResponseEntity.ok(this.servicioService.obtenerServicioPorCodigo(codigoServicio));
    }

    //guardar cliente
    @PostMapping
    public ResponseEntity<ServicioDto> guardarServicio(@Valid @RequestBody ServicioDto servicioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioService.guardarServicio(servicioDto));
    }

    //modificar cliente
    @PutMapping("{codigoServicio}")
    public ResponseEntity<ServicioDto> modificarServicio(
            @PathVariable Long codigoServicio,
            @RequestBody @Valid ModServicioDto modServicioDto) {
        ServicioDto servicioActualizado = this.servicioService.modificarServicio(codigoServicio, modServicioDto);
        return ResponseEntity.ok(servicioActualizado);
    }

    //eliminar cliente
    @DeleteMapping("{codigoServicio}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long codigoServicio) {
        this.servicioService.eliminarServicio(codigoServicio);
        return ResponseEntity.noContent().build();
    }

    //exception - clienteNoExiste - ClienteYaExiste

    //Consulta a la IA

    //Validaciones (dependencias)

    //Documentación (dependencias)

}
