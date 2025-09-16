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
    public ResponseEntity<List<ClienteDto>> obtenerTodo(){
        //200: OK
        //404: Not Found, no existe, mal nombre
        //500: Internal Server Error, error de lógica
        //405: Method de solicitud incorrecto, el verbo no es correcto
        //return this.peliculaService.obtenerTodo();
        return ResponseEntity.ok(this.clienteService.obtenerTodo());
    }
    @GetMapping("{codigo}")
    @Operation( summary = "Obtener un cliente a partir de su identificador",
            description = "Retorna al cliente que coincida con el identificador envidio",
            responses ={
                    @ApiResponse(responseCode = "200", description = "Cliente fue encontrada con exito"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrada", content = @Content)
            })

    public ResponseEntity<ClienteDto> obtenerClientePorCodigo(@Parameter(description ="Identificador del cliente a recuperar", example = "S")@PathVariable Long codigo){
        //return this.peliculaService.obtenerPeliculaPorCodigo(codigo);
        return ResponseEntity.ok(this.clienteService.obtenerClientePorCodigo(codigo));
    }
    //guardar pelicula
    @PostMapping
    public ResponseEntity<ClienteDto> guardarCliente(@RequestBody ClienteDto clienteDto){
        //return this.peliculaService.guardarPelicula(peliculaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.guardarCliente(clienteDto));
    }
    //modificar pelicula
    @PutMapping("{codigo}")
    public ResponseEntity<ClienteDto> modificarCliente(@PathVariable Long codigo, @RequestBody @Valid ModClienteDto modClienteDto){
        return ResponseEntity.ok().build();
    }
    //eliminar pelicula
    @DeleteMapping("{codigo}")
    public  ResponseEntity<ClienteDto> eliminarCliente(@PathVariable Long codigo){
        this.clienteService.eliminarCliente(codigo);
        return ResponseEntity.ok().build();
    }

    //exception - peliculaNoExiste - PeliculaYaExiste

    //Consulta a la IA

    //Validaciones (dependencias)

    //Documentación (dependencias)
}

