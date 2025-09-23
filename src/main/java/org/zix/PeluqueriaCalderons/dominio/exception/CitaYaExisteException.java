package org.zix.PeluqueriaCalderons.dominio.exception;

import java.time.LocalDateTime;

public class CitaYaExisteException extends RuntimeException {
    public CitaYaExisteException(Long clienteId, LocalDateTime fechaHora) {
        super("Ya existe una cita para el cliente con ID: " + clienteId + " en la fecha y hora: " + fechaHora);
    }
}
