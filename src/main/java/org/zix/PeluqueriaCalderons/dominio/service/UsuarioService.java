package org.zix.PeluqueriaCalderons.dominio.service;


import org.zix.PeluqueriaCalderons.dominio.dto.UsuarioDto;
import org.zix.PeluqueriaCalderons.dominio.exception.UsuarioNoExisteException;
import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;
import org.zix.PeluqueriaCalderons.dominio.repository.UsuarioEntityRepository;
import org.zix.PeluqueriaCalderons.web.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioEntityRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioDto autenticar(String username, String password) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByUsernameAndPassword(username, password);

        if (usuarioOpt.isEmpty()) {
            throw new UsuarioNoExisteException("Credenciales inválidas para el usuario: " + username);
        }

        UsuarioEntity usuario = usuarioOpt.get();

        if (!usuario.getActivo()) {
            throw new UsuarioNoExisteException("Usuario inactivo: " + username);
        }

        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto crearUsuario(UsuarioDto usuarioDto) {
        if (usuarioRepository.existsByUsername(usuarioDto.getUsername())) {
            throw new RuntimeException("El nombre de usuario '" + usuarioDto.getUsername() + "' ya existe");
        }

        UsuarioEntity usuario = usuarioMapper.toEntity(usuarioDto);
        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuarioGuardado);
    }

    public List<UsuarioDto> obtenerTodos() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new UsuarioNoExisteException("No se encontraron usuarios en el sistema");
        }
        return usuarioMapper.toDtoList(usuarios);
    }

    public List<UsuarioDto> obtenerActivos() {
        List<UsuarioEntity> usuarios = usuarioRepository.findByActivoTrue();
        if (usuarios.isEmpty()) {
            throw new UsuarioNoExisteException("No se encontraron usuarios activos");
        }
        return usuarioMapper.toDtoList(usuarios);
    }

    public List<UsuarioDto> obtenerPorRol(UsuarioEntity.Rol rol) {
        List<UsuarioEntity> usuarios = usuarioRepository.findByRol(rol);
        if (usuarios.isEmpty()) {
            throw new UsuarioNoExisteException("No se encontraron usuarios con el rol: " + rol);
        }
        return usuarioMapper.toDtoList(usuarios);
    }

    public UsuarioDto obtenerPorId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoExisteException(id));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto obtenerPorUsername(String username) {
        UsuarioEntity usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsuarioNoExisteException(username, "username"));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto actualizarUsuario(Long id, UsuarioDto usuarioDto) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoExisteException(id));

        // Verificar si el username ya existe en otro usuario
        if (usuarioRepository.existsByUsernameAndIdNot(usuarioDto.getUsername(), id)) {
            throw new RuntimeException("El nombre de usuario '" + usuarioDto.getUsername() + "' ya está en uso por otro usuario");
        }

        // Actualizar campos
        usuarioExistente.setUsername(usuarioDto.getUsername());
        if (usuarioDto.getPassword() != null && !usuarioDto.getPassword().isEmpty()) {
            usuarioExistente.setPassword(usuarioDto.getPassword());
        }
        usuarioExistente.setRol(usuarioDto.getRol());
        usuarioExistente.setActivo(usuarioDto.getActivo());

        UsuarioEntity usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDto(usuarioActualizado);
    }

    public UsuarioDto activarUsuario(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoExisteException(id));

        usuario.setActivo(true);
        UsuarioEntity usuarioActivado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuarioActivado);
    }

    public UsuarioDto desactivarUsuario(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoExisteException(id));

        usuario.setActivo(false);
        UsuarioEntity usuarioDesactivado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuarioDesactivado);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoExisteException(id);
        }
        usuarioRepository.deleteById(id);
    }

    public boolean existeUsuario(Long id) {
        return usuarioRepository.existsById(id);
    }

    public boolean existeUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }
}