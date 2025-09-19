package org.zix.PeluqueriaCalderons.web.controller;

import org.zix.PeluqueriaCalderons.dominio.dto.LoginRequest;
import org.zix.PeluqueriaCalderons.dominio.dto.LoginResponseDto;
import org.zix.PeluqueriaCalderons.dominio.dto.UsuarioDto;
import org.zix.PeluqueriaCalderons.dominio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
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
    public ResponseEntity<List<UsuarioDto>> obtenerTodos() {
        List<UsuarioDto> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<UsuarioDto>> obtenerActivos() {
        List<UsuarioDto> usuarios = usuarioService.obtenerActivos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<UsuarioDto>> obtenerPorRol(@PathVariable UsuarioEntity.Rol rol) {
        List<UsuarioDto> usuarios = usuarioService.obtenerPorRol(rol);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtenerPorId(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioDto> obtenerPorUsername(@PathVariable String username) {
        UsuarioDto usuario = usuarioService.obtenerPorUsername(username);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @Validated @RequestBody UsuarioDto usuarioDto) {
        try {
            UsuarioDto usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDto);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(new org.zix.PeluqueriaCalderons.dominio.exception.Error("error_actualizacion", ex.getMessage()));
        }
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<UsuarioDto> activarUsuario(@PathVariable Long id) {
        UsuarioDto usuarioActivado = usuarioService.activarUsuario(id);
        return ResponseEntity.ok(usuarioActivado);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<UsuarioDto> desactivarUsuario(@PathVariable Long id) {
        UsuarioDto usuarioDesactivado = usuarioService.desactivarUsuario(id);
        return ResponseEntity.ok(usuarioDesactivado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> existeUsuario(@PathVariable Long id) {
        boolean existe = usuarioService.existeUsuario(id);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/username/{username}/existe")
    public ResponseEntity<Boolean> existeUsername(@PathVariable String username) {
        boolean existe = usuarioService.existeUsername(username);
        return ResponseEntity.ok(existe);
    }
}
