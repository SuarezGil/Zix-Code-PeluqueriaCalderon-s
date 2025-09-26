package org.zix.PeluqueriaCalderons.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zix.PeluqueriaCalderons.dominio.dto.CitaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/citas")
@Tag(name = "Citas", description = "Operaciones (CRUD) sobre las citas de PeluqueriaCalderons")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // Obtener todas las citas
    @GetMapping
    @Operation(
            summary = "Obtener todas las citas",
            description = "Devuelve la lista completa de citas registradas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de" +
                            " citas obtenido con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CitaDto.class)))
            }
    )
    public ResponseEntity<List<CitaDto>> obtenerTodo() {
        return ResponseEntity.ok(this.citaService.obtenerTodo());
    }

    // Obtener cita por código
    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener una cita por su identificador",
            description = "Retorna la cita que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cita encontrada con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CitaDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
            }
    )
    public ResponseEntity<CitaDto> obtenerCitaPorCodigo(
            @Parameter(description = "Identificador de la cita a recuperar", example = "1")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.citaService.buscarCitaPorCodigo(codigo));
    }

    // Guardar nueva cita
    @PostMapping
    @Operation(
            summary = "Registrar una nueva cita",
            description = "Crea y guarda una nueva cita en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cita creada con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CitaDto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos en la petición", content = @Content)
            }
    )
    public ResponseEntity<CitaDto> guardarCita(
            @Valid @RequestBody
            @Parameter(description = "Datos de la cita a registrar") ModCitaDto modCitaDto) {
        CitaDto citaCreada = this.citaService.guardarCita(modCitaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
    }

    // Modificar cita existente
    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar una cita existente",
            description = "Actualiza los datos de una cita registrada a partir de su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cita actualizada con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CitaDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos en la petición", content = @Content)
            }
    )
    public ResponseEntity<CitaDto> modificarCita(
            @Parameter(description = "Identificador de la cita a modificar", example = "1")
            @PathVariable Long codigo,
            @Valid @RequestBody
            @Parameter(description = "Nuevos datos para la cita") ModCitaDto modCitaDto) {
        CitaDto citaActualizada = this.citaService.modificarCita(codigo, modCitaDto);
        return ResponseEntity.ok(citaActualizada);
    }

    // Eliminar cita
    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar una cita",
            description = "Elimina la cita que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cita eliminada con éxito"),
                    @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarCita(
            @Parameter(description = "Identificador de la cita a eliminar", example = "1")
            @PathVariable Long codigo) {
        this.citaService.eliminarCita(codigo);
        return ResponseEntity.noContent().build();
    }
}
