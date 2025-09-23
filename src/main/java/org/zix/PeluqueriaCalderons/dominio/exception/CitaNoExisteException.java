package org.zix.PeluqueriaCalderons.dominio.exception;

public class CitaNoExisteException extends RuntimeException {
    public CitaNoExisteException(Long codidoCita) {
        super("La citas con código: " + codidoCita + " no existe en el sitema.");
    }
}
