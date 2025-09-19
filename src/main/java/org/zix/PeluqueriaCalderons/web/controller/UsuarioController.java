package org.zix.PeluqueriaCalderons.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zix.PeluqueriaCalderons.dominio.dto.LoginRequest;
import org.zix.PeluqueriaCalderons.dominio.dto.LoginResponseDto;
import org.zix.PeluqueriaCalderons.dominio.dto.UsuarioDto;
import org.zix.PeluqueriaCalderons.dominio.service.UsuarioService;
import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones (CRUD y autenticación) sobre los usuarios de PeluqueriaCalderons")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuario",
            description = "Valida las credenciales (username y password) de un usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login exitoso"),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content)
            })
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponseDto response = usuarioService.autenticar(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new org.zix.PeluqueriaCalderons.dominio.exception.Error("error_login", ex.getMessage()));
        }
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema")
    public ResponseEntity<?> crearUsuario(@Validated @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioDto usuarioCreado = usuarioService.crearUsuario(usuarioDto);
            return ResponseEntity.ok(usuarioCreado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(new org.zix.PeluqueriaCalderons.dominio.exception.Error("error_creacion", ex.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios",
            description = "Retorna una lista de todos los usuarios registrados")
    public ResponseEntity<List<UsuarioDto>> obtenerTodos() {
        List<UsuarioDto> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/activos")
    @Operation(summary = "Obtener usuarios activos",
            description = "Retorna la lista de usuarios que se encuentran activos")
    public ResponseEntity<List<UsuarioDto>> obtenerActivos() {
        List<UsuarioDto> usuarios = usuarioService.obtenerActivos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/rol/{rol}")
    @Operation(summary = "Obtener usuarios por rol",
            description = "Devuelve los usuarios que tengan un rol específico")
    public ResponseEntity<List<UsuarioDto>> obtenerPorRol(
            @Parameter(description = "Rol del usuario", example = "ADMIN")
            @PathVariable UsuarioEntity.Rol rol) {
        List<UsuarioDto> usuarios = usuarioService.obtenerPorRol(rol);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID",
            description = "Busca un usuario por su identificador único")
    public ResponseEntity<UsuarioDto> obtenerPorId(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id) {
        UsuarioDto usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Obtener usuario por nombre de usuario",
            description = "Busca un usuario utilizando su username")
    public ResponseEntity<UsuarioDto> obtenerPorUsername(
            @Parameter(description = "Nombre de usuario", example = "jdoe")
            @PathVariable String username) {
        UsuarioDto usuario = usuarioService.obtenerPorUsername(username);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario",
            description = "Modifica los datos de un usuario existente")
    public ResponseEntity<?> actualizarUsuario(
            @Parameter(description = "ID del usuario a actualizar", example = "1")
            @PathVariable Long id,
            @Validated @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioDto usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDto);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(new org.zix.PeluqueriaCalderons.dominio.exception.Error("error_actualizacion", ex.getMessage()));
        }
    }

    @PatchMapping("/{id}/activar")
    @Operation(summary = "Activar usuario",
            description = "Activa un usuario que estaba deshabilitado")
    public ResponseEntity<UsuarioDto> activarUsuario(
            @Parameter(description = "ID del usuario a activar", example = "1")
            @PathVariable Long id) {
        UsuarioDto usuarioActivado = usuarioService.activarUsuario(id);
        return ResponseEntity.ok(usuarioActivado);
    }

    @PatchMapping("/{id}/desactivar")
    @Operation(summary = "Desactivar usuario",
            description = "Desactiva un usuario que estaba activo")
    public ResponseEntity<UsuarioDto> desactivarUsuario(
            @Parameter(description = "ID del usuario a desactivar", example = "1")
            @PathVariable Long id) {
        UsuarioDto usuarioDesactivado = usuarioService.desactivarUsuario(id);
        return ResponseEntity.ok(usuarioDesactivado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario",
            description = "Elimina un usuario de la base de datos")
    public ResponseEntity<Void> eliminarUsuario(
            @Parameter(description = "ID del usuario a eliminar", example = "1")
            @PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/existe")
    @Operation(summary = "Verificar existencia por ID",
            description = "Devuelve true si existe un usuario con el ID indicado")
    public ResponseEntity<Boolean> existeUsuario(
            @Parameter(description = "ID del usuario", example = "1")
            @PathVariable Long id) {
        boolean existe = usuarioService.existeUsuario(id);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/username/{username}/existe")
    @Operation(summary = "Verificar existencia por username",
            description = "Devuelve true si existe un usuario con el username indicado")
    public ResponseEntity<Boolean> existeUsername(
            @Parameter(description = "Nombre de usuario", example = "jdoe")
            @PathVariable String username) {
        boolean existe = usuarioService.existeUsername(username);
        return ResponseEntity.ok(existe);
    }
}
