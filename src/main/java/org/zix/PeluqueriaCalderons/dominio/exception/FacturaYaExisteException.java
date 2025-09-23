package org.zix.PeluqueriaCalderons.dominio.exception;

import java.time.LocalDateTime;

public class FacturaYaExisteException extends RuntimeException {
    public FacturaYaExisteException(Long facturaId) {
        super("Ya existe una factura con ID: " + facturaId);
    }
}
