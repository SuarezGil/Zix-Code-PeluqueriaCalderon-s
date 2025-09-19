package org.zix.PeluqueriaCalderons.dominio.exception;

public class ClienteNoExisteException extends RuntimeException {
    public ClienteNoExisteException(Long codigoCliente) {

        super("El cliente codigo no existe: " + codigoCliente);
    }
}
