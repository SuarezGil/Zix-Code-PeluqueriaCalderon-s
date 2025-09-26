package org.zix.PeluqueriaCalderons.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
import org.zix.PeluqueriaCalderons.dominio.service.ServicioService;

import java.util.List;

@RestController
@RequestMapping("/servicios/v1")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los servicios")
    public ResponseEntity<List<ServicioDto>> obtenerTodos() {
        return ResponseEntity.ok(servicioService.obtenerTodo());
    }

    @GetMapping("/{codigoServicio}")
    @Operation(summary = "Obtener un servicio por ID")
    public ResponseEntity<ServicioDto> obtenerServicio(
            @Parameter(description = "ID del servicio a recuperar")
            @PathVariable Long codigoServicio) {
        return ResponseEntity.ok(servicioService.obtenerServicioPorCodigo(codigoServicio));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo servicio")
    public ResponseEntity<ServicioDto> crearServicio(@RequestBody @Valid ServicioDto servicioDto) {
        ServicioDto servicioCreado = servicioService.guardarServicio(servicioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioCreado);
    }

    @PutMapping("/{codigoServicio}")
    @Operation(summary = "Actualizar un servicio")
    public ResponseEntity<ServicioDto> actualizarServicio(
            @PathVariable Long codigoServicio,
            @RequestBody @Valid ModServicioDto modServicioDto) {
        ServicioDto servicioActualizado = servicioService.modificarServicio(codigoServicio, modServicioDto);
        return ResponseEntity.ok(servicioActualizado);
    }

    @DeleteMapping("/{codigoServicio}")
    @Operation(summary = "Eliminar un servicio")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long codigoServicio) {
        servicioService.eliminarServicio(codigoServicio);
        return ResponseEntity.noContent().build();
    }
}
