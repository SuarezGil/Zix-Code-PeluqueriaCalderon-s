package org.zix.PeluqueriaCalderons.web.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.zix.PeluqueriaCalderons.dominio.dto.LoginResponseDto;
import org.zix.PeluqueriaCalderons.dominio.service.UsuarioService;

import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private LoginResponseDto usuarioLogueado;

    @Autowired
    private UsuarioService usuarioService;

    public String login() {
        try {
            // Validar campos vacíos
            if (username == null || username.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario es obligatorio"));
                return null;
            }

            if (password == null || password.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña es obligatoria"));
                return null;
            }

            // Autenticar usuario
            LoginResponseDto response = usuarioService.autenticar(username.trim(), password);
            this.usuarioLogueado = response;

            // Guardar usuario en sesión
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("usuarioLogueado", response);

            // Limpiar campos
            this.username = null;
            this.password = null;

            // Redirigir al menú principal
            return "/MenuPrincipal.xhtml?faces-redirect=true";

        } catch (Exception ex) {
            // Mostrar mensaje de error específico
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de autenticación", ex.getMessage()));

            // Limpiar password por seguridad
            this.password = null;
            return null;
        }
    }

    public String logout() {
        // Invalidar sesión
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    // Método para verificar si hay usuario logueado
    public boolean isLogueado() {
        return usuarioLogueado != null;
    }

    // Método para proteger páginas
    public void verificarSesion() {
        try {
            // Verificar si hay usuario en sesión
            if (usuarioLogueado == null) {
                // Intentar recuperar de la sesión
                usuarioLogueado = (LoginResponseDto) FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().get("usuarioLogueado");
            }

            // Si aún es null, redirigir al login
            if (usuarioLogueado == null) {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("index.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GETTERS Y SETTERS COMPLETOS (IMPORTANTE)
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

    public LoginResponseDto getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(LoginResponseDto usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    // Getter para el servicio (opcional, pero recomendado)
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}