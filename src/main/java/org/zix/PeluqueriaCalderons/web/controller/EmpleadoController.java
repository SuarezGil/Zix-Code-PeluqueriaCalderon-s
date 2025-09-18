package org.zix.PeluqueriaCalderons.web.controller;

import org.zix.PeluqueriaCalderons.dominio.dto.EmpleadoDto;
import org.zix.PeluqueriaCalderons.dominio.dto.CreateEmpleadoDto;
import org.zix.PeluqueriaCalderons.dominio.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> getAllEmpleados() {
        List<EmpleadoDto> empleados = empleadoService.getAllEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<EmpleadoDto>> getEmpleadosActivos() {
        List<EmpleadoDto> empleados = empleadoService.getEmpleadosActivos();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/puesto/{puesto}")
    public ResponseEntity<List<EmpleadoDto>> getEmpleadosByPuesto(@PathVariable String puesto) {
        List<EmpleadoDto> empleados = empleadoService.getEmpleadosByPuesto(puesto);
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> getEmpleadoById(@PathVariable Long id) {
        EmpleadoDto empleado = empleadoService.getEmpleadoById(id);
        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmpleadoDto> getEmpleadoByEmail(@PathVariable String email) {
        EmpleadoDto empleado = empleadoService.getEmpleadoByEmail(email);
        return ResponseEntity.ok(empleado);
    }

    @PostMapping
    public ResponseEntity<?> createEmpleado(@Validated @RequestBody CreateEmpleadoDto empleadoDto) {
        EmpleadoDto nuevoEmpleado = empleadoService.createEmpleado(empleadoDto);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@PathVariable Long id, @Validated @RequestBody EmpleadoDto empleadoDto) {
        EmpleadoDto empleadoActualizado = empleadoService.updateEmpleado(id, empleadoDto);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<EmpleadoDto> activarEmpleado(@PathVariable Long id) {
        EmpleadoDto empleado = empleadoService.activateEmpleado(id);
        return ResponseEntity.ok(empleado);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<EmpleadoDto> desactivarEmpleado(@PathVariable Long id) {
        EmpleadoDto empleado = empleadoService.deactivateEmpleado(id);
        return ResponseEntity.ok(empleado);
    }
}
