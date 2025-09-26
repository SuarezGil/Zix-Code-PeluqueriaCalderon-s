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
import org.zix.PeluqueriaCalderons.dominio.dto.ClienteDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModClienteDto;
import org.zix.PeluqueriaCalderons.dominio.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes/v1")
@Tag(name = "Clientes", description = "Operaciones (CRUD) sobre los clientes de PeluqueriaCalderons")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Obtener todos los clientes
    @GetMapping
    @Operation(
            summary = "Obtener todos los clientes",
            description = "Devuelve la lista completa de clientes registrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de clientes obtenido con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteDto.class)))
            }
    )
    public ResponseEntity<List<ClienteDto>> obtenerTodo() {
        return ResponseEntity.ok(this.clienteService.obtenerTodo());
    }

    // Obtener cliente por código
    @GetMapping("{codigoCliente}")
    @Operation(
            summary = "Obtener un cliente por su identificador",
            description = "Retorna al cliente que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
            }
    )
    public ResponseEntity<ClienteDto> obtenerClientePorCodigo(
            @Parameter(description = "Identificador del cliente a recuperar", example = "1")
            @PathVariable Long codigoCliente) {
        return ResponseEntity.ok(this.clienteService.obtenerClientePorCodigo(codigoCliente));
    }

    // Guardar nuevo cliente
    @PostMapping
    @Operation(
            summary = "Registrar un nuevo cliente",
            description = "Crea y guarda un nuevo cliente en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cliente creado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteDto.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos en la petición", content = @Content),
                    @ApiResponse(responseCode = "409", description = "El cliente ya existe", content = @Content)
            }
    )
    public ResponseEntity<ClienteDto> guardarCliente(
            @Valid @RequestBody
            @Parameter(description = "Datos del cliente a registrar") ClienteDto clienteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.guardarCliente(clienteDto));
    }

    // Modificar cliente existente
    @PutMapping("{codigoCliente}")
    @Operation(
            summary = "Modificar un cliente existente",
            description = "Actualiza los datos de un cliente a partir de su identificador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ClienteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos en la petición", content = @Content)
            }
    )
    public ResponseEntity<ClienteDto> modificarCliente(
            @Parameter(description = "Identificador del cliente a modificar", example = "1")
            @PathVariable Long codigoCliente,
            @Valid @RequestBody
            @Parameter(description = "Nuevos datos para el cliente") ModClienteDto modClienteDto) {
        ClienteDto clienteActualizado = this.clienteService.modificarCliente(codigoCliente, modClienteDto);
        return ResponseEntity.ok(clienteActualizado);
    }

    // Eliminar cliente
    @DeleteMapping("{codigoCliente}")
    @Operation(
            summary = "Eliminar un cliente",
            description = "Elimina el cliente que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarCliente(
            @Parameter(description = "Identificador del cliente a eliminar", example = "1")
            @PathVariable Long codigoCliente) {
        this.clienteService.eliminarCliente(codigoCliente);
        return ResponseEntity.noContent().build();
    }
}
