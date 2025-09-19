package org.zix.PeluqueriaCalderons.web.controller;

import org.zix.PeluqueriaCalderons.dominio.dto.EmpleadoDto;
import org.zix.PeluqueriaCalderons.dominio.dto.CreateEmpleadoDto;
import org.zix.PeluqueriaCalderons.dominio.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@Tag(name = "Empleados", description = "Operaciones (CRUD) sobre los empleados de PeluqueriaCalderons")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Obtener todos los empleados
    @GetMapping
    @Operation(
            summary = "Obtener todos los empleados",
            description = "Devuelve la lista completa de empleados registrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de empleados obtenido con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class)))
            }
    )
    public ResponseEntity<List<EmpleadoDto>> getAllEmpleados() {
        List<EmpleadoDto> empleados = empleadoService.getAllEmpleados();
        return ResponseEntity.ok(empleados);
    }

    // Obtener empleados activos
    @GetMapping("/activos")
    @Operation(
            summary = "Obtener empleados activos",
            description = "Devuelve la lista de empleados cuyo estado es activo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de empleados activos obtenido con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class)))
            }
    )
    public ResponseEntity<List<EmpleadoDto>> getEmpleadosActivos() {
        List<EmpleadoDto> empleados = empleadoService.getEmpleadosActivos();
        return ResponseEntity.ok(empleados);
    }

    // Obtener empleados por puesto
    @GetMapping("/puesto/{puesto}")
    @Operation(
            summary = "Obtener empleados por puesto",
            description = "Devuelve la lista de empleados que coinciden con un puesto específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de empleados filtrado por puesto obtenido con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class))),
                    @ApiResponse(responseCode = "404", description = "No se encontraron empleados con ese puesto", content = @Content)
            }
    )
    public ResponseEntity<List<EmpleadoDto>> getEmpleadosByPuesto(
            @Parameter(description = "Nombre del puesto a filtrar", example = "Estilista")
            @PathVariable String puesto) {
        List<EmpleadoDto> empleados = empleadoService.getEmpleadosByPuesto(puesto);
        return ResponseEntity.ok(empleados);
    }

    // Obtener empleado por ID
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener un empleado por su identificador",
            description = "Devuelve la información de un empleado a partir de su identificador único",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado encontrado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class))),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> getEmpleadoById(
            @Parameter(description = "Identificador del empleado", example = "1")
            @PathVariable Long id) {
        EmpleadoDto empleado = empleadoService.getEmpleadoById(id);
        return ResponseEntity.ok(empleado);
    }

    // Obtener empleado por email
    @GetMapping("/email/{email}")
    @Operation(
            summary = "Obtener un empleado por su correo electrónico",
            description = "Devuelve la información de un empleado que coincida con el email enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado encontrado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class))),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> getEmpleadoByEmail(
            @Parameter(description = "Correo electrónico del empleado", example = "empleado@correo.com")
            @PathVariable String email) {
        EmpleadoDto empleado = empleadoService.getEmpleadoByEmail(email);
        return ResponseEntity.ok(empleado);
    }

    // Crear empleado
    @PostMapping
    @Operation(
            summary = "Registrar un nuevo empleado",
            description = "Crea y guarda un nuevo empleado en el sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado creado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos en la petición", content = @Content),
                    @ApiResponse(responseCode = "409", description = "El empleado ya existe", content = @Content)
            }
    )
    public ResponseEntity<?> createEmpleado(
            @Validated @RequestBody
            @Parameter(description = "Datos para crear un nuevo empleado") CreateEmpleadoDto empleadoDto) {
        EmpleadoDto nuevoEmpleado = empleadoService.createEmpleado(empleadoDto);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un empleado existente",
            description = "Modifica los datos de un empleado a partir de su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado actualizado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class))),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos en la petición", content = @Content)
            }
    )
    public ResponseEntity<?> updateEmpleado(
            @Parameter(description = "Identificador del empleado", example = "1")
            @PathVariable Long id,
            @Validated @RequestBody
            @Parameter(description = "Nuevos datos del empleado") EmpleadoDto empleadoDto) {
        EmpleadoDto empleadoActualizado = empleadoService.updateEmpleado(id, empleadoDto);
        return ResponseEntity.ok(empleadoActualizado);
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un empleado",
            description = "Elimina el registro del empleado a partir de su identificador",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empleado eliminado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content)
            }
    )
    public ResponseEntity<Void> deleteEmpleado(
            @Parameter(description = "Identificador del empleado a eliminar", example = "1")
            @PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    // Activar empleado
    @PatchMapping("/{id}/activar")
    @Operation(
            summary = "Activar un empleado",
            description = "Cambia el estado del empleado a activo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado activado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class))),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> activarEmpleado(
            @Parameter(description = "Identificador del empleado", example = "1")
            @PathVariable Long id) {
        EmpleadoDto empleado = empleadoService.activateEmpleado(id);
        return ResponseEntity.ok(empleado);
    }

    // Desactivar empleado
    @PatchMapping("/{id}/desactivar")
    @Operation(
            summary = "Desactivar un empleado",
            description = "Cambia el estado del empleado a inactivo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado desactivado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmpleadoDto.class))),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> desactivarEmpleado(
            @Parameter(description = "Identificador del empleado", example = "1")
            @PathVariable Long id) {
        EmpleadoDto empleado = empleadoService.deactivateEmpleado(id);
        return ResponseEntity.ok(empleado);
    }
}
