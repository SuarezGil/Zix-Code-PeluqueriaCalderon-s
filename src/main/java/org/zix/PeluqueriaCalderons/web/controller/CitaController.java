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
import org.zix.PeluqueriaCalderons.dominio.dto.ModCitaDto;
import org.zix.PeluqueriaCalderons.dominio.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/citas/v1")
@Tag(name = "Citas", description = "Operaciones (CRUD) sobre las citas de PeluqueriaCalderons")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // Obtener todas las citas
    @GetMapping
    public ResponseEntity<List<CitaDto>> obtenerTodo() {
        return ResponseEntity.ok(this.citaService.obtenerTodo());
    }

    // Obtener cita por código
    @GetMapping("{codigo}")
    @Operation(summary = "Obtener una cita a partir de su identificador",
            description = "Retorna la cita que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cita encontrada con éxito"),
                    @ApiResponse(responseCode = "404", description = "Cita no encontrada", content = @Content)
            })
    public ResponseEntity<CitaDto> obtenerCitaPorCodigo(
            @Parameter(description = "Identificador de la cita a recuperar", example = "1")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.citaService.buscarCitaPorCodigo(codigo));
    }

    // Guardar nueva cita
    @PostMapping
    public ResponseEntity<CitaDto> guardarCita(@Valid @RequestBody ModCitaDto modCitaDto) {
        CitaDto citaCreada = this.citaService.guardarCita(modCitaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
    }


    // Modificar cita existente
    @PutMapping("{codigo}")
    public ResponseEntity<CitaDto> modificarCita(
            @PathVariable Long codigo,
            @RequestBody @Valid ModCitaDto modCitaDto) {
        CitaDto citaActualizada = this.citaService.modificarCita(codigo, modCitaDto);
        return ResponseEntity.ok(citaActualizada);
    }

    // Eliminar cita
    @DeleteMapping("{codigo}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long codigo) {
        this.citaService.eliminarCita(codigo);
        return ResponseEntity.noContent().build();
    }

    // Aquí se podrían agregar endpoints adicionales o consultas personalizadas si se requieren
}
