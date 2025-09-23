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
@Tag(name = "Citas", description = "Operaciones (CRUD) sobre las citas")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }


    @GetMapping
    public ResponseEntity<List<CitaDto>> obtenerTodo(){
        return ResponseEntity.ok(this.citaService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    public ResponseEntity<CitaDto> buscarPorId(@PathVariable Long codigo){
        CitaDto citaDto = this.citaService.buscarPorId(codigo);
        return ResponseEntity.ok(citaDto);  // Retorna la cita
    }


    @PostMapping
    public ResponseEntity<CitaDto> guardarCita(@RequestBody @Valid CitaDto citaDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.citaService.guardarCita(citaDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<CitaDto> modificarCita
    (@PathVariable Long codigo, @RequestBody @Valid ModCitaDto modCitaDto){
        return ResponseEntity.ok(this.citaService.modificarCita(codigo, modCitaDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<CitaDto> eliminarCita(@PathVariable Long codigo){
        this.citaService.eliminarCita(codigo);
        return ResponseEntity.ok().build();
    }
}
