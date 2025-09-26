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
import org.zix.PeluqueriaCalderons.dominio.dto.ModServicioDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ServicioDto;
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

    @GetMapping
    @Operation(summary = "Obtener todos los servicios",
            description = "Devuelve una lista con todos los servicios disponibles en la peluquería")
    public ResponseEntity<List<ServicioDto>> obtenerTodo() {
        return ResponseEntity.ok(this.servicioService.obtenerTodo());
    }

    @GetMapping("{codigoServicio}")
    @Operation(summary = "Obtener un servicio por ID",
            description = "Retorna el servicio que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Servicio encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Servicio no encontrado", content = @Content)
            })
    public ResponseEntity<ServicioDto> obtenerServicioPorCodigo(
            @Parameter(description = "Identificador del servicio a recuperar", example = "1")
            @PathVariable Long codigoServicio) {
        return ResponseEntity.ok(this.servicioService.obtenerServicioPorCodigo(codigoServicio));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo servicio",
            description = "Crea un nuevo servicio y lo guarda en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Servicio creado con éxito"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            })
    public ResponseEntity<ServicioDto> guardarServicio(
            @Valid @RequestBody ServicioDto servicioDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.servicioService.guardarServicio(servicioDto));
    }

    @PutMapping("{codigoServicio}")
    @Operation(summary = "Modificar un servicio existente",
            description = "Actualiza los datos de un servicio en base a su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Servicio actualizado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Servicio no encontrado", content = @Content)
            })
    public ResponseEntity<ServicioDto> modificarServicio(
            @Parameter(description = "Identificador del servicio a modificar", example = "1")
            @PathVariable Long codigoServicio,
            @RequestBody @Valid ModServicioDto modServicioDto) {
        ServicioDto servicioActualizado = this.servicioService.modificarServicio(codigoServicio, modServicioDto);
        return ResponseEntity.ok(servicioActualizado);
    }

    @DeleteMapping("{codigoServicio}")
    @Operation(summary = "Eliminar un servicio",
            description = "Elimina el servicio asociado al identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Servicio eliminado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Servicio no encontrado", content = @Content)
            })
    public ResponseEntity<Void> eliminarServicio(
            @Parameter(description = "Identificador del servicio a eliminar", example = "1")
            @PathVariable Long codigoServicio) {
        this.servicioService.eliminarServicio(codigoServicio);
        return ResponseEntity.noContent().build();
    }
}
