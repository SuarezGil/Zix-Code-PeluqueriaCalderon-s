package org.zix.PeluqueriaCalderons.dominio.exception;

public class FacturaNoExisteException extends RuntimeException {
    public FacturaNoExisteException(Long codigoFactura) {

        super("El codigo de la factura no existe: " + codigoFactura);
    }
}
