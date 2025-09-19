package org.zix.PeluqueriaCalderons.dominio.dto;


import org.zix.PeluqueriaCalderons.persistence.entity.UsuarioEntity;
import java.time.LocalDateTime;

public class LoginResponseDto {
    private Long id;
    private String username;
    private UsuarioEntity.Rol rol;
    private LocalDateTime fechaCreacion;
    private Boolean activo;
    private String mensaje;

    public LoginResponseDto() {}

    public LoginResponseDto(Long id, String username, UsuarioEntity.Rol rol,
                            LocalDateTime fechaCreacion, Boolean activo, String mensaje) {
        this.id = id;
        this.username = username;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
        this.mensaje = mensaje;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public UsuarioEntity.Rol getRol() { return rol; }
    public void setRol(UsuarioEntity.Rol rol) { this.rol = rol; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
