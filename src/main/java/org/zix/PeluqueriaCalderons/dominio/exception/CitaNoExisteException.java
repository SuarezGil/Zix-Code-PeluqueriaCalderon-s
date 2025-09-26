package org.zix.PeluqueriaCalderons.dominio.exception;

public class CitaNoExisteException extends RuntimeException {
    public CitaNoExisteException(Long codigo) {
        super("Cita no encontrada: " + codigo);
    }
}
