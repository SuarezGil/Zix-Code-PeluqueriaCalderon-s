package org.zix.PeluqueriaCalderons.dominio.dto;

import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;
import java.time.LocalDateTime;

public class UsuarioDto {

    private Long id;
    private String username;
    private String password;
    private UsuarioEntity.Rol rol;
    private LocalDateTime fechaCreacion;
    private Boolean activo;

    // Constructores
    public UsuarioDto() {}

    public UsuarioDto(Long id, String username, String password, UsuarioEntity.Rol rol,
                      LocalDateTime fechaCreacion, Boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioEntity.Rol getRol() {
        return rol;
    }

    public void setRol(UsuarioEntity.Rol rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", rol=" + rol +
                ", fechaCreacion=" + fechaCreacion +
                ", activo=" + activo +
                '}';
    }
}