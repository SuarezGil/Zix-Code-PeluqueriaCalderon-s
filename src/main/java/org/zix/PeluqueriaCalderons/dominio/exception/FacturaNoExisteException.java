package org.zix.PeluqueriaCalderons.dominio.exception;

public class FacturaNoExisteException extends RuntimeException {
    public FacturaNoExisteException(Long codigoFactura) {
        super("La factura con código: " + codigoFactura + " no existe en el sitema.");
    }
}