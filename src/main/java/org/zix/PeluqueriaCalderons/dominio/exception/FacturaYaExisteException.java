package org.zix.PeluqueriaCalderons.dominio.exception;

public class FacturaYaExisteException extends RuntimeException {
    public FacturaYaExisteException(Long codigoFactura) {
        super("Ya existe una factura con ID: " + codigoFactura);
    }
}