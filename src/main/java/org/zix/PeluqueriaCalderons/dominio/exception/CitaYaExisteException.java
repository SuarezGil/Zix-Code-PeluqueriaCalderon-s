package org.zix.PeluqueriaCalderons.dominio.exception;

import java.time.LocalDateTime;

public class CitaYaExisteException extends RuntimeException {
    public CitaYaExisteException(LocalDateTime fechaHora) {

        super("Esta hora y fecha ya esta programada" + fechaHora);
    }
}
