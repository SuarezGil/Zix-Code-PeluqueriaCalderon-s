package org.zix.PeluqueriaCalderons.dominio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNoExisteException extends RuntimeException {

    public UsuarioNoExisteException() {
        super("Usuario no encontrado");
    }

    public UsuarioNoExisteException(String mensaje) {
        super(mensaje);
    }

    public UsuarioNoExisteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public UsuarioNoExisteException(Long id) {
        super("Usuario con ID " + id + " no encontrado");
    }

    public UsuarioNoExisteException(String username, String tipo) {
        super("Usuario con " + tipo + " '" + username + "' no encontrado");
    }
}
