package org.zix.PeluqueriaCalderons.dominio.exception;

public class ClienteNoExisteException extends RuntimeException {
    public ClienteNoExisteException(Long codigo) {

        super("El cliente codigo no existe: " + codigo);
    }
}
