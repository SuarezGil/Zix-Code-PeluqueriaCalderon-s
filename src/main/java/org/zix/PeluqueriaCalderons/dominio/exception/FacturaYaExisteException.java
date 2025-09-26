package org.zix.PeluqueriaCalderons.dominio.exception;

import java.time.LocalDateTime;

public class FacturaYaExisteException extends RuntimeException {
    public FacturaYaExisteException(LocalDateTime date, Long clienteId) {

        super("La factura con fecha y hora :"+ date +", con codigo del cliente:"+ clienteId + "ya esta registrado ");
    }
}
