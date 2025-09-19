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

    //4
    @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerTodo() {
        return ResponseEntity.ok(this.clienteService.obtenerTodo());
    }

    @GetMapping("{codigoCliente}")
    @Operation(summary = "Obtener un cliente a partir de su identificador",
            description = "Retorna al cliente que coincida con el identificador enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente fue encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
            })
    public ResponseEntity<ClienteDto> obtenerClientePorCodigo(
            @Parameter(description = "Identificador del cliente a recuperar", example = "1")
            @PathVariable Long codigoCliente) {
        return ResponseEntity.ok(this.clienteService.obtenerClientePorCodigo(codigoCliente));
    }

    //guardar cliente
    @PostMapping
    public ResponseEntity<ClienteDto> guardarCliente(@Valid  @RequestBody ClienteDto clienteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.guardarCliente(clienteDto));
    }

    //modificar cliente
    @PutMapping("{codigoCliente}")
    public ResponseEntity<ClienteDto> modificarCliente(
            @PathVariable Long codigoCliente,
            @RequestBody @Valid ModClienteDto modClienteDto) {
        ClienteDto clienteActualizado = this.clienteService.modificarCliente(codigoCliente, modClienteDto);
        return ResponseEntity.ok(clienteActualizado);
    }

    //eliminar cliente
    @DeleteMapping("{codigoCliente}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long codigoCliente) {
        this.clienteService.eliminarCliente(codigoCliente);
        return ResponseEntity.noContent().build();
    }

    //exception - clienteNoExiste - ClienteYaExiste

    //Consulta a la IA

    //Validaciones (dependencias)

    //Documentación (dependencias)
}
