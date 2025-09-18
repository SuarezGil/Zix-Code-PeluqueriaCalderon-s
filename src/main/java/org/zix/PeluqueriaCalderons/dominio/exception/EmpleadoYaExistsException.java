package org.zix.PeluqueriaCalderons.dominio.exception;

public class EmpleadoYaExistsException extends RuntimeException {
    public EmpleadoYaExistsException(String message) {
        super(message);
    }
}
