package org.zix.PeluqueriaCalderons.dominio.exception;

public class ClienteYaExisteException extends RuntimeException {
    public ClienteYaExisteException(String correo) {

        super("Ya existe un cliente con el codigo: " + correo);
    }
}
