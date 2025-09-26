package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.zix.PeluqueriaCalderons.dominio.dto.UsuarioDto;

@Named("usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable {

    private List<UsuarioDto> usuarios;
    private UsuarioDto usuarioActual;
    private UsuarioDto usuarioSeleccionado;

    private final String API_URL = "http://localhost:8090/usuarios";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        cargarUsuarios();
        if (usuarioActual == null) {
            usuarioActual = new UsuarioDto();
        }
    }

    public void cargarUsuarios() {
        UsuarioDto[] lista = restTemplate.getForObject(API_URL, UsuarioDto[].class);
        usuarios = Arrays.asList(lista);
    }

    public void prepararNuevo() {
        usuarioActual = new UsuarioDto();
        usuarioActual.setActivo(true); // por defecto activo
    }

    public void prepararEdicion(UsuarioDto u) {
        if (u != null) {
            usuarioActual = u;
        } else {
            usuarioActual = new UsuarioDto();
        }
    }

    public void guardarUsuario() {
        if (usuarioActual.getId() == null) {
            restTemplate.postForObject(API_URL, usuarioActual, UsuarioDto.class);
        } else {
            restTemplate.put(API_URL + "/" + usuarioActual.getId(), usuarioActual);
        }
        cargarUsuarios();
    }

    public void eliminarUsuario(Long id) {
        restTemplate.delete(API_URL + "/" + id);
        cargarUsuarios();
    }

    // Getters y Setters
    public List<UsuarioDto> getUsuarios() { return usuarios; }
    public UsuarioDto getUsuarioActual() { return usuarioActual; }
    public void setUsuarioActual(UsuarioDto usuarioActual) { this.usuarioActual = usuarioActual; }
    public UsuarioDto getUsuarioSeleccionado() { return usuarioSeleccionado; }
    public void setUsuarioSeleccionado(UsuarioDto usuarioSeleccionado) { this.usuarioSeleccionado = usuarioSeleccionado; }
}
